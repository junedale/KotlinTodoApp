import controller.MainController
import util.DatabaseUtil
import view.base.WindowInterface
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

object MainWindow : JFrame(), WindowInterface {

    // The init block initializes the property of the object or class
    init {
        title = "Todo App"
        layout = null
        background = Color(220, 220, 220)
        size = Dimension(400, 600)

        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE

    }

    // Function addComponent adds components to the content pane of the JFrame
    override fun addComponent(component: Component) {
        contentPane.add(component)
        contentPane.repaint()
        contentPane.validate()
    }

    // Function removeComponent removes content pane components
    override fun removeComponent() {
        contentPane.removeAll()
        contentPane.repaint()
        contentPane.validate()
    }

    // Function changeView changes the view of the JFrame
    override fun changeView(view: JPanel) {
        removeComponent()
        addComponent(view)
    }

}

fun main(args: Array<String>) {
    DatabaseUtil.connect()
    MainWindow
    MainController
}