package view.base

import java.awt.Component
import javax.swing.JPanel
import javax.swing.JScrollPane

interface ViewInterface {

    val scrollContentContainer: JPanel
    val scrollPane: JScrollPane

    fun reloadView() {
        scrollContentContainer.removeAll()
        scrollContentContainer.repaint()
        scrollContentContainer.validate()
        scrollPane.validate()
    }

    fun addComponent(component: Component) {
        scrollContentContainer.add(component)
    }

    fun validateView() {
        scrollContentContainer.validate()
        scrollPane.validate()
    }

}