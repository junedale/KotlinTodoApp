package view.component

import util.Constant
import view.component.base.GradientProgressBarUI
import java.awt.Color
import java.awt.Dimension
import javax.swing.*

class SubToDoCard : JPanel() {
    val ongoing: JLabel
    val edit: JButton
    val remove: JButton
    private val startDate: JLabel
    private val progress: JProgressBar
    private val taskName: JLabel
    private val total: JLabel
    private val dueDate: JLabel
    private val spring: SpringLayout = SpringLayout()

    // The init block initializes the property of the object or class
    init {
        layout = spring
        background = Color.WHITE
        preferredSize = Dimension(360, 180)

        ongoing = JLabel("0")
        startDate = JLabel()
        dueDate = JLabel()
        progress = JProgressBar()
        taskName = JLabel()
        total = JLabel()
        remove = JButton()
        edit = JButton()
        initUI()
    }

    // function initUI initializes the position of controls and add them to the panel
    private fun initUI() {
        spring.putConstraint(SpringLayout.WEST, taskName, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, taskName, 10, SpringLayout.NORTH, this)
        add(taskName)

        progress.setUI(GradientProgressBarUI())
        progress.isBorderPainted = false
        progress.preferredSize = Dimension(320, 15)
        spring.putConstraint(SpringLayout.WEST, progress, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, progress, 58, SpringLayout.NORTH, total)
        add(progress)

        total.font = Constant.title
        total.foreground = Color.GRAY
        spring.putConstraint(SpringLayout.WEST, total, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, total, 20, SpringLayout.NORTH, taskName)
        add(total)

        edit.isFocusable = false
        edit.isFocusPainted = false
        edit.isBorderPainted = false
        edit.isContentAreaFilled = false
        edit.preferredSize = Dimension(40, 40)
        edit.icon = ImageIcon("./src/main/resources/icons8-edit-24.png")
        spring.putConstraint(SpringLayout.EAST, edit, 50, SpringLayout.EAST, total)
        spring.putConstraint(SpringLayout.NORTH, edit, 35, SpringLayout.NORTH, this)
        add(edit)

        val startDateLabel = JLabel("Start: ")
        startDateLabel.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, startDateLabel, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, startDateLabel, 25, SpringLayout.NORTH, progress)
        add(startDateLabel)

        startDate.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, startDate, 40, SpringLayout.WEST, startDateLabel)
        spring.putConstraint(SpringLayout.NORTH, startDate, 25, SpringLayout.NORTH, progress)
        add(startDate)

        val dueDateLabel = JLabel("Finish: ")
        dueDateLabel.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, dueDateLabel, 156, SpringLayout.WEST, startDate)
        spring.putConstraint(SpringLayout.NORTH, dueDateLabel, 25, SpringLayout.NORTH, progress)
        add(dueDateLabel)

        dueDate.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, dueDate, 50, SpringLayout.WEST, dueDateLabel)
        spring.putConstraint(SpringLayout.NORTH, dueDate, 25, SpringLayout.NORTH, progress)
        add(dueDate)

        val ongoingLabel = JLabel("Ongoing:")
        ongoingLabel.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, ongoingLabel, 20, SpringLayout.WEST, this)
        spring.putConstraint(SpringLayout.NORTH, ongoingLabel, 25, SpringLayout.NORTH, startDateLabel)
        add(ongoingLabel)

        ongoing.font = Constant.small
        spring.putConstraint(SpringLayout.WEST, ongoing, 60, SpringLayout.WEST, ongoingLabel)
        spring.putConstraint(SpringLayout.NORTH, ongoing, 26, SpringLayout.NORTH, startDateLabel)
        add(ongoing)

        remove.isFocusable = false
        remove.isFocusPainted = false
        remove.isBorderPainted = false
        remove.isContentAreaFilled = false
        remove.preferredSize = Dimension(40, 40)
        remove.icon = ImageIcon("./src/main/resources/icons8-close-24.png")
        spring.putConstraint(SpringLayout.EAST, remove, 0, SpringLayout.EAST, this)
        spring.putConstraint(SpringLayout.NORTH, remove, 0, SpringLayout.NORTH, this)
        add(remove)

    }

    /*
     function setData function sets the value for taskName, startDate, dueDate, progress, and total.
     this function also set the action command for remove and edit button
     */
    fun setData(data: List<String>) {
        remove.actionCommand = "mvd${data[0]}"
        edit.actionCommand = "mve${data[0]}"
        taskName.text = data[1]
        startDate.text = data[2]
        dueDate.text = data[3]
        progress.value = data[4].toInt()
        total.text = "${data[4]} %"
    }
}