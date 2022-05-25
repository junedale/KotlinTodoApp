package view.component

import util.Constant

import java.awt.Color
import java.awt.Dimension
import javax.swing.*


class TaskCard: JPanel() {
    val checkBox: JCheckBox
    val taskTitle: JLabel
    private val spring: SpringLayout = SpringLayout()

    init {
        layout = spring
        preferredSize = Dimension(330, 40)
        maximumSize = Dimension(330, 40)
        border = BorderFactory.createLineBorder(Color.BLACK)

        checkBox = JCheckBox()
        taskTitle = JLabel("Create the navbar")

        initUI()
    }

    private fun initUI() {
        spring.putConstraint(SpringLayout.WEST, checkBox, 1, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, checkBox, 8, SpringLayout.NORTH, this)
        add(checkBox)

        taskTitle.font = Constant.normal
        spring.putConstraint(SpringLayout.WEST, taskTitle, 32, SpringLayout.WEST, checkBox)
        spring.putConstraint(SpringLayout.NORTH, taskTitle, 6, SpringLayout.NORTH, this)
        add(taskTitle)
    }
}