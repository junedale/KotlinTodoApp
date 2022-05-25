package view.view

import view.component.CButton
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.border.EmptyBorder

object MainView: JPanel() {
    val scrollContentContainer: JPanel
    val scrollPane: JScrollPane
    val add: JButton
    init {
        layout = null
        scrollContentContainer = JPanel()
        scrollPane = JScrollPane()
        add = CButton()
        maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        initUI()
    }

    private fun initUI() {
        scrollContentContainer.layout = BoxLayout(scrollContentContainer, BoxLayout.Y_AXIS)
        scrollContentContainer.border = EmptyBorder(10, 10, 10, 10)
        add(scrollContentContainer)

        add.text = "Add Todo"
        add.setBounds(10, 560, 120, 30)
        add(add)

        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollPane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        scrollPane.border = BorderFactory.createEmptyBorder()
        scrollPane.setViewportView(scrollContentContainer)
        scrollPane.setBounds(0, 50, 400, 500)
        add(scrollPane)
    }
}