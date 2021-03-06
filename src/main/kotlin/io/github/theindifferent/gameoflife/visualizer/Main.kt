package io.github.theindifferent.gameoflife.visualizer

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import javax.swing.SwingUtilities

@SpringBootApplication
open class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplicationBuilder(Main::class.java)
                .headless(false)
                .web(WebApplicationType.SERVLET)
                .run(*args)

            val gui = context.getBean(VisualizerUI::class.java)
            SwingUtilities.invokeLater(gui)
            context.getBean(GenerationTimer::class.java)
                    .startTimer()
        }
    }
}
