package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.BorderLayout
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EtchedBorder

@Component
class VisualizerUI : Runnable {
    override fun run() {
        val gameOfLifeComponent = GameOfLifeUI()

        val contentPane = JPanel(BorderLayout())
        contentPane.border = BorderFactory
                .createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED))
        contentPane.add(gameOfLifeComponent)

        val frame = JFrame("Game Of Life")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = contentPane
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}
