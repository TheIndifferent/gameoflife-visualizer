package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.border.EtchedBorder

@Component
class VisualizerUI(val gameOfLifeComponent: GameOfLifeUI) : Runnable {
    override fun run() {
        val contentPane = JPanel(BorderLayout())
        contentPane.border = BorderFactory
                .createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED))
        contentPane.add(gameOfLifeComponent, BorderLayout.CENTER)
        contentPane.add(createGridSizeButtonsBox(), BorderLayout.NORTH)

        val frame = JFrame("Game Of Life")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = contentPane

        gameOfLifeComponent.addKeyListener(object: KeyAdapter() {
            override fun keyTyped(e: KeyEvent?) {
                val keyChar = e?.keyChar ?: return
                when (keyChar) {
                    '-', '_' -> gameOfLifeComponent.decreaseGridSize()
                    '+', '=' -> gameOfLifeComponent.increaseGridSize()
                    else -> return
                }
            }
        })

        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        gameOfLifeComponent.requestFocus()
    }

    private fun createGridSizeButtonsBox() : JComponent {
        val increaseButton = JButton("+")
        increaseButton.addActionListener({ gameOfLifeComponent.increaseGridSize() })

        val decreaseButton = JButton("-")
        decreaseButton.addActionListener({ gameOfLifeComponent.decreaseGridSize() })

        val box = Box.createHorizontalBox()
        box.add(Box.createHorizontalGlue())
        box.add(increaseButton)
        box.add(Box.createHorizontalStrut(10))
        box.add(decreaseButton)
        box.add(Box.createHorizontalGlue())
        return box
    }
}
