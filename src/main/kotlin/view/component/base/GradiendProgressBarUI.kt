package view.component.base

import java.awt.Color
import java.awt.Graphics
import java.awt.LinearGradientPaint
import java.awt.geom.Point2D
import java.awt.geom.RoundRectangle2D
import java.awt.image.BufferedImage
import java.awt.image.PixelGrabber
import javax.swing.JComponent
import javax.swing.SwingConstants
import javax.swing.plaf.basic.BasicProgressBarUI

internal class GradientProgressBarUI : BasicProgressBarUI() {
    private val pallet: IntArray

    init {
        pallet = makeGradientPallet()
    }

    public override fun paintDeterminate(g: Graphics, c: JComponent) {
        val b = progressBar.insets // area for border
        val barRectWidth = progressBar.width - b.right - b.left
        val barRectHeight = progressBar.height - b.top - b.bottom
        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return
        }
        val amountFull = getAmountFull(b, barRectWidth, barRectHeight)
        if (progressBar.orientation == SwingConstants.HORIZONTAL) {
            val x = amountFull / barRectWidth.toFloat()
            g.color = getColorFromPallet(pallet, x)
            g.fillRect(b.left, b.top, amountFull, barRectHeight)
        } else {
            val y = amountFull / barRectHeight.toFloat()
            g.color = getColorFromPallet(pallet, y)
            g.fillRect(b.left, barRectHeight + b.bottom - amountFull, barRectWidth, amountFull)
        }
        if (progressBar.isStringPainted) {
            paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b)
        }
    }

    companion object {
        private fun makeGradientPallet(): IntArray {
            val image = BufferedImage(100, 1, BufferedImage.TYPE_INT_RGB)
            val g2 = image.createGraphics()
            val start: Point2D = Point2D.Float(0f, 0f)
            val end: Point2D = Point2D.Float(99f, 0f)
            val dist = floatArrayOf(0f, .5f, 1f)
            val colors = arrayOf(Color.RED, Color.YELLOW, Color.GREEN)
            g2.paint = LinearGradientPaint(start, end, dist, colors)
            g2.fillRect(0, 0, 100, 1)
            g2.dispose()
            val width = image.getWidth(null)
            val pallet = IntArray(width)
            val pg = PixelGrabber(image, 0, 0, width, 1, pallet, 0, width)
            try {
                pg.grabPixels()
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
            return pallet
        }

        private fun getColorFromPallet(pallet: IntArray, x: Float): Color {
            require(!(x < 0.0 || x > 1.0)) { "Parameter outside of expected range" }
            val i = (pallet.size * x).toInt()
            val max = pallet.size - 1
            val index = if (i < 0) 0 else if (i > max) max else i
            return Color(pallet[index] and 0x00ffffff)
        }
    }
}