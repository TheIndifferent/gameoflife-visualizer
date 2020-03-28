package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JComponent

@Component
class GameOfLifeUI(val model: GameOfLifeModel) : JComponent(),
        GameOfLifeModelListener {

    init {
        // idiomatic Swing, add self as listener:
        model.addModelListener(this)
        setPreferredSize(Dimension(800, 800))
    }

    override fun paintComponent(graphicsNullable: Graphics?) {
        val g = graphicsNullable!!
        val bounds: Rectangle = g.getClipBounds() ?: Rectangle(0, 0, getSize().width, getSize().height)
        g.setColor(Color.DARK_GRAY)
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
    }

    override fun fireModelUpdate() {
        repaint()
    }
}
