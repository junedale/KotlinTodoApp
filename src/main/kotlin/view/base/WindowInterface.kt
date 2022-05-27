package view.base

import java.awt.Component
import javax.swing.JPanel

interface WindowInterface {
    fun addComponent(component: Component)
    fun removeComponent()
    fun changeView(view: JPanel)
}