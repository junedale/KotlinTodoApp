package controller

import FormWindow
import MainWindow
import model.Task
import model.Todo
import view.component.MainToDoCard
import view.component.TaskCard
import view.view.MainFormView
import view.view.MainView
import view.view.TaskFormView
import view.view.TaskView
import java.awt.Component
import java.awt.Dimension
import java.awt.event.*
import javax.swing.Box
import javax.swing.JCheckBox
import javax.swing.JOptionPane

object MainController {
    private val parent = MainWindow

    private val mView  = MainView
    private val tView  = TaskView
    private val fView  = MainFormView
    private val tfView = TaskFormView

    private val todo = Todo
    private val task = Task

    private val mViewComponentList = ArrayList<Component>()
    private val tViewComponentList = ArrayList<Component>()
    private val spacerList = ArrayList<Component>()

    private val btnListener = ButtonListener()
    private val checkListener = CheckboxListener()

    private var currentId = ""

    // The init block initializes the property of the object or class
    init {
        parent.changeView(mView)
        mainViewOnLoad()
        mView.addBtn.addActionListener(btnListener)
        tView.addBtn.addActionListener(btnListener)
        tView.backBtn.addActionListener(btnListener)
        fView.confirm.addActionListener(btnListener)
        tfView.confirm.addActionListener(btnListener)
        tView.taskDetailCard.remove.addActionListener(btnListener)
        tView.taskDetailCard.edit.addActionListener(btnListener)
    }

    // Function clearList removes all dynamically created object
    private fun clearList() {
        mViewComponentList.clear()
        tViewComponentList.clear()
        spacerList.clear()
    }

    // Function mainViewOnLoad fetch data from the database then add it in the view
    private fun mainViewOnLoad() {
        for(item in todo.findAll()) {
            mViewComponentList.add(MainToDoCard(btnListener, item))
            spacerList.add(Box.createRigidArea(Dimension(0, 8)))
            mView.addComponent(mViewComponentList.last())
            mView.addComponent(spacerList.last())
        }
        mView.validateView()
    }

    // Function mainViewOnLoad fetch data from the database then add it in the view
    private fun taskViewOnLoad(taskId: String) {
        tView.taskDetailCard.setData(todo.find(taskId))
        for(item in task.findAll(currentId)) {
            tViewComponentList.add(TaskCard(item, btnListener, checkListener))
            spacerList.add(Box.createRigidArea(Dimension(0, 8)))
            tView.addComponent(tViewComponentList.last())
            tView.addComponent(spacerList.last())
        }
        tView.validateView()
        task.computeProgress(currentId, tViewComponentList)
        tView.taskDetailCard.ongoing.text = task.ongoing.toString()
    }

    // Function validateForm performs basic form validation
    fun validateForm(actionCommand: String): Boolean {
        if(actionCommand == "mvConfirm" || actionCommand == "mConfirm") {
            if(fView.titleField.text == "" || fView.startDate.text == "" || fView.dueDate.text == "")
                return false
        } else {
            if(tfView.titleField.text == "")
                return false
        }
        return true
    }

    // CheckboxListener listens to checkbox state change then updates the status field
    class CheckboxListener : ItemListener {
        override fun itemStateChanged(e: ItemEvent) {
            val source = e.source as JCheckBox
            if(e.stateChange == ItemEvent.SELECTED) {
                task.updateField(source.actionCommand, "status", "1")
            } else {
                task.updateField(source.actionCommand, "status", "0")
            }
            todo.updateField(currentId, "progress", task.computeProgress(currentId, tViewComponentList).toString())
            tView.taskDetailCard.ongoing.text = task.ongoing.toString()
            tView.reloadView()
            tViewComponentList.clear()
            taskViewOnLoad(currentId)
        }
    }


    class ButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            when {
                // Deletes a task to the database and the view
                e.actionCommand.startsWith("tv") -> {
                    task.delete(e.actionCommand.slice(2 until e.actionCommand.length))
                    tView.reloadView()
                    clearList()
                    taskViewOnLoad(currentId)
                }
                // Deletes a task to the database and the view
                e.actionCommand.startsWith("mvd") -> {
                    todo.delete(e.actionCommand.slice(3 until e.actionCommand.length))
                    clearList()
                    tView.reloadView()
                    mView.reloadView()
                    mainViewOnLoad()
                    parent.changeView(mView)
                }
                // Shows the edit form
                e.actionCommand.startsWith("mve") -> {
                    println(e.actionCommand)
                    FormWindow
                    fView.confirm.actionCommand = "mvConfirm"
                    FormWindow.changeView(fView)
                    FormWindow.isVisible = true
                }
                // Validates the form and inserts data to the database and updates the view
                e.actionCommand == "mConfirm" -> {
                    println(e.actionCommand)
                    if(validateForm(e.actionCommand)) {
                        FormWindow.isVisible = false
                        todo.insert(currentId, fView.titleField.text, fView.startDate.text, fView.dueDate.text)
                        mView.reloadView()
                        clearList()
                        mainViewOnLoad()
                    } else {
                        JOptionPane.showMessageDialog(parent, "Please complete the form", "Incomplete form", JOptionPane.ERROR_MESSAGE)
                    }
                }
                // Validates the form and updates the database and view
                e.actionCommand == "mvConfirm" -> {
                    println(e.actionCommand)
                    if(validateForm(e.actionCommand)) {
                        FormWindow.isVisible = false
                        todo.update(currentId, fView.titleField.text, fView.startDate.text, fView.dueDate.text)
                        tView.reloadView()
                        clearList()
                        taskViewOnLoad(currentId)
                    } else {
                        JOptionPane.showMessageDialog(parent, "Please complete the form", "Incomplete form", JOptionPane.ERROR_MESSAGE)
                    }
                }
                // Validates the form and inserts data to the database and updates the view
                e.actionCommand == "tConfirm" -> {
                    println(e.actionCommand)
                    if(validateForm(e.actionCommand)) {
                        FormWindow.isVisible = false
                        task.insert(currentId,  tfView.titleField.text)
                        tView.reloadView()
                        clearList()
                        taskViewOnLoad(currentId)
                    } else {
                        JOptionPane.showMessageDialog(parent, "Please complete the form", "Incomplete form", JOptionPane.ERROR_MESSAGE)
                    }
                }
                // Shows the add project/to-do form
                e.actionCommand == "mAdd" -> {
                    fView.confirm.actionCommand = "mConfirm"
                    println(e.actionCommand)
                    FormWindow
                    FormWindow.changeView(fView)
                    FormWindow.isVisible = true
                }
                // Shows the add project/to-do form
                e.actionCommand =="tAdd" -> {
                    fView.confirm.actionCommand = "tConfirm"
                    println(e.actionCommand)
                    FormWindow
                    FormWindow.changeView(tfView)
                    FormWindow.isVisible = true
                }
                // Changes the view
                e.actionCommand =="return" -> {
                    clearList()
                    parent.changeView(mView)
                    tView.reloadView()
                    mView.reloadView()
                    mainViewOnLoad()
                }
                // Changes the view
                else -> {
                    clearList()
                    currentId = e.actionCommand
                    parent.changeView(tView)
                    taskViewOnLoad(currentId)
                }
            }
        }
    }
}