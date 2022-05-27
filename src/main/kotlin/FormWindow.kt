import view.base.WindowInterface
import java.awt.Component
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

object FormWindow : JFrame(), WindowInterface {

    // The init block initializes the property of the object or class
    init {
        title = "Form Window"
        size = Dimension(380, 200)
        isVisible = true
        defaultCloseOperation = HIDE_ON_CLOSE
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
