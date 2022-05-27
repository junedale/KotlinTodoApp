package view.view

import view.base.ViewInterface
import view.component.CButton
import java.awt.Color
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.SpringLayout
import javax.swing.border.EmptyBorder

object MainView: JPanel(), ViewInterface {
    override val scrollContentContainer: JPanel
    override val scrollPane: JScrollPane
    val addBtn: JButton
    private val spring = SpringLayout()

    // The init block initializes the property of the object or class
    init {
        layout = spring
        scrollContentContainer = JPanel()
        scrollPane = JScrollPane()
        addBtn = CButton()
        setBounds(0, 0, 400, 600)
        initUI()
    }

    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        scrollContentContainer.layout = BoxLayout(scrollContentContainer, BoxLayout.Y_AXIS)
        scrollContentContainer.border = EmptyBorder(10, 10, 10, 10)
        add(scrollContentContainer)

        addBtn.text = "Add Task"
        addBtn.foreground = Color.WHITE
        addBtn.preferredSize = Dimension(120, 30)
        addBtn.actionCommand = "mAdd"
        spring.putConstraint(SpringLayout.WEST, addBtn, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.SOUTH, addBtn, -50, SpringLayout.SOUTH, this)
        add(addBtn)

        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollPane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        scrollPane.border = BorderFactory.createEmptyBorder()
        scrollPane.setViewportView(scrollContentContainer)
        scrollPane.maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        scrollPane.preferredSize = Dimension(384, 500)
        spring.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, this)
        add(scrollPane)
    }
}