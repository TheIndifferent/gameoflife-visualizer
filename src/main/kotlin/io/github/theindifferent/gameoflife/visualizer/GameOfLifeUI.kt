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

    private val backgroundColor: Color = Color(57, 64, 69)
    private var gridSize: Int = 5

    init {
        // idiomatic Swing, add self as listener:
        model.addModelListener(this)
        setPreferredSize(Dimension(800, 800))
    }

    override fun paintComponent(graphicsNullable: Graphics?) {
        val g = graphicsNullable!!
        val clip: Rectangle = g.getClipBounds() ?: Rectangle(0, 0, getSize().width, getSize().height)
        g.setColor(backgroundColor)
        g.fillRect(clip.x, clip.y, clip.width, clip.height)
        g.setColor(Color.WHITE)

        model.cells()
            .filter { it.x >= clip.x
                    && it.y >= clip.y
                    && it.x <= clip.width / gridSize
                    && it.y <= clip.height / gridSize }
            .forEach {
                g.fillRect(it.x * gridSize, it.y * gridSize, gridSize, gridSize)
            }
    }

    override fun fireModelUpdate() {
        repaint()
    }
}
