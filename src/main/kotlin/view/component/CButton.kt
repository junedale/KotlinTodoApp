package view.component

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JButton

class CButton : JButton() {
    init {
        isFocusable = false
        isFocusPainted = false
        isBorderPainted = false
        isContentAreaFilled = false
        foreground = Color.WHITE
        val size = preferredSize
        size.height = kotlin.math.max(size.width, size.height)
        size.width = size.height
    }

    override fun paintComponent(g: Graphics) {
        val g2D = g as Graphics2D
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        if (getModel().isArmed) {
            g2D.color = Color(127, 86, 204)
        } else {
            g2D.color = Color(137, 94, 218)
        }
        g2D.fillRoundRect(0, 0, width - 1, height - 1, 30, 30)
        super.paintComponent(g)
    }

    override fun paintBorder(g: Graphics) {
        val g2D = g as Graphics2D
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2D.color = Color(137, 94, 218)
        g2D.drawRoundRect(0, 0, width - 1, height - 1, 30, 30)
        super.paintBorder(g)
    }
}