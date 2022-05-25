package view.component

import util.Constant
import view.component.base.GradientProgressBarUI
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JProgressBar
import javax.swing.SpringLayout

class MainToDoCard(private val listener: ActionListener, private  val gotoIndex: Int) : JPanel() {
    private val spring = SpringLayout()
    val taskTitle: JLabel
    val startDate: JLabel
    val dueDate: JLabel
    val progress: JProgressBar
    private val goto: JButton

    init {
        layout = spring
        taskTitle = JLabel("Create Vue App")
        startDate = JLabel("06/21/01")
        dueDate   = JLabel("06/21/01")
        progress  = JProgressBar()
        goto      = JButton()

        isOpaque = true
        background = Color.WHITE
        preferredSize = Dimension(365, 100)
        maximumSize = Dimension(Int.MAX_VALUE, 100)
        initUI()
    }

    private fun initUI() {
        val taskLabel = JLabel("Todo")
        taskLabel.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, taskLabel, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskLabel, 10, SpringLayout.NORTH, this)
        add(taskLabel)

        spring.putConstraint(SpringLayout.WEST, taskTitle, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskTitle, 14, SpringLayout.NORTH, taskLabel)
        taskTitle.font = Constant.subtitle
        add(taskTitle)

        val dateGrid = JPanel(GridLayout(1, 4, 2, 0))
        dateGrid.background = Color.WHITE

        val startDateLabel = JLabel("Start: ")
        startDateLabel.font = Constant.small
        startDate.font = Constant.small


        val dueDateLabel = JLabel("Finish: ")
        dueDateLabel.font = Constant.small
        dueDate.font = Constant.small

        dateGrid.add(startDateLabel)
        dateGrid.add(startDate)
        dateGrid.add(dueDateLabel)
        dateGrid.add(dueDate)
        spring.putConstraint(SpringLayout.WEST, dateGrid, 10, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, dateGrid, 28, SpringLayout.NORTH, taskTitle)
        add(dateGrid)

        goto.icon = ImageIcon("./src/main/resources/icons8-chevron-48.png")
        goto.isFocusable = false
        goto.isBorderPainted = false
        goto.isFocusPainted = false
        goto.isContentAreaFilled = false
        goto.preferredSize = Dimension(30, 50)
        goto.addActionListener(listener)
        goto.actionCommand = gotoIndex.toString()
        spring.putConstraint(SpringLayout.EAST, goto, -5, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.NORTH, goto, 20, SpringLayout.NORTH, this)
        add(goto)

        progress.setUI(GradientProgressBarUI())
        progress.value = 40
        progress.isBorderPainted = false
        progress.isOpaque = false
        progress.preferredSize = Dimension(365, 15)
        spring.putConstraint(SpringLayout.WEST, progress, 0, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.SOUTH, progress, 0, SpringLayout.SOUTH, this)
        add(progress)
    }

}