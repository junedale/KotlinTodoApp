package view.view

import util.Constant
import view.component.CButton
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SpringLayout

object MainFormView: JPanel() {
    val titleField: JTextField = JTextField()
    val startDate = JTextField()
    val dueDate = JTextField()
    val confirm = CButton()
    private val spring = SpringLayout()

    // The init block initializes the property of the object or class
    init {
        layout = spring
        maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        initUI()
    }

    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        val titleLabel = JLabel("Todo Title")
        titleLabel.font = Constant.normal
        spring.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this)
        add(titleLabel)

        titleField.preferredSize = Dimension(340, 24)
        spring.putConstraint(SpringLayout.WEST, titleField, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, titleField, 24, SpringLayout.NORTH, titleLabel)
        add(titleField)

        val startDateLabel = JLabel("Start Date:")
        startDateLabel.font = Constant.normal
        spring.putConstraint(SpringLayout.WEST, startDateLabel, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, startDateLabel, 28, SpringLayout.NORTH, titleField)
        add(startDateLabel)

        startDate.preferredSize = Dimension(160, 24)
        spring.putConstraint(SpringLayout.WEST, startDate, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, startDate, 24, SpringLayout.NORTH, startDateLabel)
        add(startDate)

        val dueDateLabel = JLabel("Due Date:")
        dueDateLabel.font = Constant.normal
        spring.putConstraint(SpringLayout.WEST, dueDateLabel, 180, SpringLayout.WEST, startDateLabel)
        spring.putConstraint(SpringLayout.NORTH, dueDateLabel, 28, SpringLayout.NORTH, titleField)
        add(dueDateLabel)

        dueDate.preferredSize = Dimension(160, 24)
        spring.putConstraint(SpringLayout.EAST, dueDate, -14, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.NORTH, dueDate, 24, SpringLayout.NORTH, dueDateLabel)
        add(dueDate)

        confirm.text = "Confirm"
        confirm.actionCommand = "mConfirm"
        confirm.preferredSize = Dimension(160, 30)
        spring.putConstraint(SpringLayout.WEST, confirm, 100, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.SOUTH, confirm, -10, SpringLayout.SOUTH, this)
        add(confirm)
    }


}