package view.view

import util.Constant
import view.base.ViewInterface
import view.component.CButton
import view.component.SubToDoCard
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

object TaskView : JPanel(), ViewInterface {
    override val scrollContentContainer: JPanel
    override val scrollPane: JScrollPane
    val addBtn: JButton
    val backBtn: JButton
    val taskDetailCard: SubToDoCard
    private val spring = SpringLayout()


    // The init block initializes the property of the object or class
    init {
        layout = spring
        setBounds(0, 0, 400, 600)
        scrollContentContainer = JPanel()
        scrollPane = JScrollPane(scrollContentContainer,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        taskDetailCard = SubToDoCard()
        addBtn = CButton()
        backBtn = CButton()
        initUI()
    }

    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        spring.putConstraint(SpringLayout.WEST, taskDetailCard, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskDetailCard, 10, SpringLayout.NORTH, this)
        add(taskDetailCard)

        val container = JPanel()
        container.layout = FlowLayout(FlowLayout.CENTER, 0, 8)
        container.background = Color.white
        container.preferredSize = Dimension(360, 300)
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

        scrollPane.preferredSize = Dimension(360, 260)
        scrollPane.border = BorderFactory.createEmptyBorder()
        container.add(scrollPane)

        addBtn.text = "Add Task"
        addBtn.foreground = Color.WHITE
        addBtn.preferredSize = Dimension(120, 30)
        addBtn.actionCommand = "tAdd"
        spring.putConstraint(SpringLayout.EAST, addBtn, -30, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.SOUTH, addBtn, -50, SpringLayout.SOUTH, this)
        add(addBtn)

        backBtn.text = "Return"
        backBtn.foreground = Color.WHITE
        backBtn.preferredSize = Dimension(120, 30)
        backBtn.actionCommand = "return"
        spring.putConstraint(SpringLayout.WEST, backBtn, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.SOUTH, backBtn, -50, SpringLayout.SOUTH, this)
        add(backBtn)
    }

}