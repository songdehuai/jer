import io.nacular.doodle.application.Application
import io.nacular.doodle.application.application
import io.nacular.doodle.core.Display
import io.nacular.doodle.core.WindowGroup
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.constraints.center
import io.nacular.doodle.layout.constraints.constrain
import org.kodein.di.instance
import view.MainView

class JerApplication(windows: WindowGroup, display: Display) : Application {
    init {
        val main = windows.main

        main.title = "Jer"
        main.size = Size(800.0, 600.0)

        with(display) {
            this += MainView().apply {
                suggestSize(Size(800, 600))
            }
            layout = constrain(first(), center)
        }

        println("jer application running..")
    }

    override fun shutdown() {
        println("jer application shutdown..")
    }
}


fun main() {
    application(

    ) {
        JerApplication(instance(), instance())
    }
}
