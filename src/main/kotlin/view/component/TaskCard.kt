package view.component

import util.Constant

import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionListener
import java.awt.event.ItemListener
import javax.swing.*


class TaskCard(data: List<String>, listener: ActionListener, private val checkListener: ItemListener): JPanel() {
    private val checkBox: JCheckBox
    private val taskTitle: JLabel
    private val remove: JButton
    private val spring: SpringLayout = SpringLayout()

    // The init block initializes the property of the object or class
    init {
        layout = spring
        preferredSize = Dimension(330, 40)
        maximumSize = Dimension(330, 40)
        border = BorderFactory.createLineBorder(Color.BLACK)

        checkBox = JCheckBox()
        checkBox.isSelected = data[3] == "1"
        checkBox.actionCommand = data[0]
        remove = JButton()
        remove.actionCommand = "tv" + data[0]
        remove.addActionListener(listener)
        remove.icon = ImageIcon("./src/main/resources/icons8-close-24.png")
        taskTitle = JLabel(data[2])
        initUI()
    }
    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        checkBox.addItemListener(checkListener)
        spring.putConstraint(SpringLayout.WEST, checkBox, 1, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, checkBox, 8, SpringLayout.NORTH, this)
        add(checkBox)

        taskTitle.font = Constant.normal
        spring.putConstraint(SpringLayout.WEST, taskTitle, 32, SpringLayout.WEST, checkBox)
        spring.putConstraint(SpringLayout.NORTH, taskTitle, 8, SpringLayout.NORTH, this)
        add(taskTitle)

        remove.isFocusable = false
        remove.isFocusPainted = false
        remove.isBorderPainted = false
        remove.isContentAreaFilled = false
        remove.preferredSize = Dimension(40, 40)
        spring.putConstraint(SpringLayout.EAST, remove, 0, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.NORTH, remove, 0, SpringLayout.NORTH, this)
        add(remove)
    }
}