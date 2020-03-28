package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

@Component
class GameOfLifeUI(val model: GameOfLifeModel) : JComponent(),
        GameOfLifeModelListener {

    init {
        model.addModelListener(this)
        setPreferredSize(Dimension(800, 800))
    }

    override fun paintComponent(graphicsNullable: Graphics?) {
        val g = graphicsNullable!!
        g.setColor(Color.DARK_GRAY)
        g.fillRect(0, 0, g.clipBounds!!.width, g.clipBounds!!.height)
    }

    override fun fireModelUpdate() {
        repaint()
    }
}
