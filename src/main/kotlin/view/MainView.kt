package view

import io.nacular.doodle.controls.buttons.ToggleButton
import io.nacular.doodle.core.View
import io.nacular.doodle.core.view


class MainView() : View() {

    private val handle = view {
        this.children += ToggleButton("BUTTON").apply {
            selectedChanged += { _, _, selected ->
                println("Selected: $selected")
            }
        }
    }

    init {
        children += handle
    }
}