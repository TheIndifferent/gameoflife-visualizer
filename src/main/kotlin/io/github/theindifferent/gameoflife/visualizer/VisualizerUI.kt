package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.awt.BorderLayout
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
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
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
