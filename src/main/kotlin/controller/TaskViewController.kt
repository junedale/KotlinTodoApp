package controller

import view.component.TaskCard
import view.view.MainView
import view.view.TaskView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Box
import javax.swing.JPanel

class TaskViewController(private val view: TaskView, private val window: MainWindowController) {
    private val componentList: ArrayList<JPanel> = ArrayList()
//    private var currentIndex = 0
    private val btnListener = ButtonListener()

    init {
        view.addBtn.addActionListener(btnListener)
        view.backBtn.addActionListener(btnListener)
    }

    inner class ButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            when(e.actionCommand) {
                "return" -> {
                    window.removeWindowComponent()
                    window.addWindowComponent(MainView)
                }
                "add" -> {
                    componentList.add(TaskCard())
                    val component = componentList.last()
                    view.scrollContentContainer.add(component)
                    view.scrollContentContainer.add(Box.createRigidArea(Dimension(0, 8)))
                    view.scrollContentContainer.validate()
                    view.scrollPane.validate()
                }
            }
        }

    }
}