package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EtchedBorder

@Component
class VisualizerUI(
    val gameOfLifeComponent: GameOfLifeUI,
    val generationTimer: GenerationTimer) : Runnable {
    override fun run() {
        val contentPane = JPanel(BorderLayout())
        contentPane.add(gameOfLifeComponent, BorderLayout.CENTER)

        val frame = JFrame("Game Of Life ( press +/- to change size, F/S to go faster/slower )")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = contentPane

        gameOfLifeComponent.addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent?) {
                val keyChar = e?.keyChar ?: return
                when (keyChar) {
                    '-', '_' -> gameOfLifeComponent.decreaseGridSize()
                    '+', '=' -> gameOfLifeComponent.increaseGridSize()
                    'f', 'F' -> generationTimer.increaseGenerationsPerSecond()
                    's', 'S' -> generationTimer.decreaseGenerationsPerSecond()
                    else -> return
                }
            }
        })

        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        gameOfLifeComponent.requestFocus()
    }
}
