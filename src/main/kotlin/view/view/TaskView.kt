package view.view

import util.Constant
import view.component.CButton
import view.component.SubToDoCard
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

object TaskView : JPanel() {
    val scrollContentContainer: JPanel
    val addBtn: JButton
    val backBtn: JButton
    val scrollPane: JScrollPane
    private val spring = SpringLayout()

    init {
        layout = spring
        maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        scrollContentContainer = JPanel()
        scrollPane = JScrollPane(scrollContentContainer,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        addBtn = CButton()
        backBtn = CButton()

        initUI()
    }

    private fun initUI() {
        val taskDetailCard = SubToDoCard()
        taskDetailCard.progress.value = 20
        taskDetailCard.total.text = "${taskDetailCard.progress.value} %"
        spring.putConstraint(SpringLayout.WEST, taskDetailCard, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskDetailCard, 50, SpringLayout.NORTH, this)
        add(taskDetailCard)

        val container = JPanel()
        container.layout = FlowLayout(FlowLayout.CENTER, 0, 8)
        container.background = Color.white
        container.preferredSize = Dimension(380, 300)
        spring.putConstraint(SpringLayout.WEST, container, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, container, 190, SpringLayout.NORTH, taskDetailCard)
        add(container)

        val taskLabel = JLabel("Task")
        taskLabel.font = Constant.subtitle
        taskLabel.border = EmptyBorder(0, 20, 0, 0)
        taskLabel.preferredSize = Dimension(380, 26)
        container.add(taskLabel)

        scrollContentContainer.layout = BoxLayout(scrollContentContainer, BoxLayout.Y_AXIS)
        scrollContentContainer.background = Color.WHITE

        scrollPane.preferredSize = Dimension(380, 260)
        scrollPane.border = BorderFactory.createEmptyBorder()
        container.add(scrollPane)

        addBtn.text = "Add Task"
        addBtn.foreground = Color.WHITE
        addBtn.preferredSize = Dimension(120, 30)
        addBtn.actionCommand = "add"
        spring.putConstraint(SpringLayout.EAST, addBtn, -10, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.SOUTH, addBtn, -10, SpringLayout.SOUTH, this)
        add(addBtn)

        backBtn.text = "Return"
        backBtn.foreground = Color.WHITE
        backBtn.preferredSize = Dimension(120, 30)
        backBtn.actionCommand = "return"
        spring.putConstraint(SpringLayout.WEST, backBtn, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.SOUTH, backBtn, -10, SpringLayout.SOUTH, this)
        add(backBtn)
    }
}