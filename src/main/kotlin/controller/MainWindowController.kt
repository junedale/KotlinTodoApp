package controller

import view.MainWindow
import view.view.MainView
import view.view.TaskView
import java.awt.Component
import java.awt.Point
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

class MainWindowController(private val window: MainWindow) : MouseListener, MouseMotionListener {
    private val point = Point()

    init {
        window.addMouseListener(this)
        window.addMouseMotionListener(this)
        window.mainView.scrollPane.addMouseListener(this)
        window.mainView.scrollPane.addMouseMotionListener(this)
//        window.taskView.scrollPane.addMouseListener(this)
//        window.taskView.scrollPane.addMouseMotionListener(this)
        MainViewController(MainView, this)
        TaskViewController(TaskView, this)
    }

    override fun mousePressed(e: MouseEvent) {
        point.x = e.x
        point.y = e.y
    }

    override fun mouseDragged(e: MouseEvent) {
        window.setLocation(window.location.x + e.x - point.x, window.location.y + e.y - point.y)
    }

    override fun mouseClicked(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mouseMoved(e: MouseEvent?) {}

    fun addWindowComponent(component: Component) {
        window.add(component)
        window.contentPane.repaint()
        window.contentPane.validate()
        println("Hello")
    }

    fun removeWindowComponent(){
        window.contentPane.removeAll()
        window.contentPane.repaint()
        window.contentPane.validate()
    }

}