package view

import view.view.MainView
import view.view.TaskView
import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame

object MainWindow : JFrame() {
    val mainView: MainView

    init {
        background = Color(220, 220, 220)
        size = Dimension(400, 600)

        mainView = MainView
        add(mainView)

        isUndecorated = true
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE

    }

}