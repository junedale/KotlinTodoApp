package view.view

import util.Constant
import view.component.CButton
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object TaskFormView: JPanel() {
    val titleField = JTextField()
    val confirm = CButton()

    // The init block initializes the property of the object or class
    init {
        layout = FlowLayout()
        maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        initUI()
    }
    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        val titleLabel = JLabel("Task Title: ")
        titleLabel.font = Constant.normal
        add(titleLabel)
        titleField.preferredSize = Dimension(300, 24)
        add(titleField)

        confirm.text = "Confirm"
        confirm.actionCommand = "tConfirm"
        preferredSize = Dimension(80, 24)
        add(confirm)
    }
}