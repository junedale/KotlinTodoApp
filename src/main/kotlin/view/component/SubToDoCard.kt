package view.component

import util.Constant
import view.component.base.GradientProgressBarUI
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JProgressBar
import javax.swing.SpringLayout

class SubToDoCard : JPanel() {
    val progress: JProgressBar
    val taskName: JLabel
    val total: JLabel
    val daysLeft: JLabel
    val ongoing: JLabel
    val dueDate: JLabel
    val startDate: JLabel
    val spring: SpringLayout = SpringLayout()


    init {
        layout = spring
        background = Color.WHITE
        preferredSize = Dimension(380, 180)

        progress = JProgressBar()
        taskName = JLabel("Create Vue App")
        total = JLabel(progress.value.toString() + " %")
        daysLeft = JLabel("30")
        ongoing = JLabel("3")
        dueDate = JLabel("06/21/01")
        startDate = JLabel("06/21/01")
        initUI()
    }

    private fun initUI() {
        spring.putConstraint(SpringLayout.WEST, taskName, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskName, 10, SpringLayout.NORTH, this)
        add(taskName)

        total.font = Constant.title
        total.foreground = Color.GRAY
        spring.putConstraint(SpringLayout.WEST, total, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, total, 20, SpringLayout.NORTH, taskName)
        add(total)

        progress.setUI(GradientProgressBarUI())
        progress.isBorderPainted = false
        progress.preferredSize = Dimension(340, 15)
        spring.putConstraint(SpringLayout.WEST, progress, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, progress, 58, SpringLayout.NORTH, total)
        add(progress)

        val infoGrid = JPanel(GridLayout(2, 4, 0, 4))
        spring.putConstraint(SpringLayout.WEST, infoGrid, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, infoGrid, 28, SpringLayout.NORTH, progress)
        infoGrid.background = Color.WHITE

        val startDateLabel = JLabel("Start: ")
        startDateLabel.font = Constant.small
        startDate.font = Constant.small

        val dueDateLabel = JLabel("Finish: ")
        dueDateLabel.font = Constant.small
        dueDate.font = Constant.small

        val daysLeftLabel = JLabel("Days Left:")
        daysLeftLabel.font = Constant.small
        daysLeft.font = Constant.small

        val ongoingLabel = JLabel("Ongoing:")
        ongoingLabel.font = Constant.small
        ongoing.font = Constant.small

        infoGrid.add(startDateLabel)
        infoGrid.add(startDate)
        infoGrid.add(dueDateLabel)
        infoGrid.add(dueDate)
        infoGrid.add(daysLeftLabel)
        infoGrid.add(daysLeft)
        infoGrid.add(ongoingLabel)
        infoGrid.add(ongoing)

        infoGrid.setBounds(19, 105, 341, 50)
        add(infoGrid)
    }
}