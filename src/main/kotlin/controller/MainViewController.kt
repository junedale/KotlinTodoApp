package controller

import view.component.MainToDoCard
import view.view.MainView
import view.view.TaskView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Box
import javax.swing.JPanel


class MainViewController(private val view: MainView, window: MainWindowController) {
    private val componentList: ArrayList<JPanel> = ArrayList()
    private val addTodo = AddTodoListener(componentList)
    private val gotoListener = GotoViewListener(window)
    private var currentIndex = 0

    init {
        view.add.addActionListener(addTodo)
    }

    inner class AddTodoListener(private  val list: ArrayList<JPanel>) : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            list.add(MainToDoCard(gotoListener, currentIndex++))
            val component = list.last()
            view.scrollContentContainer.add(component)
            view.scrollContentContainer.add(Box.createRigidArea(Dimension(0, 8)))
            view.scrollContentContainer.validate()
            view.scrollPane.validate()
            view.validate()
        }
    }

    inner class GotoViewListener(private val window: MainWindowController) : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            window.removeWindowComponent()
            window.addWindowComponent(TaskView)
        }

    }
}