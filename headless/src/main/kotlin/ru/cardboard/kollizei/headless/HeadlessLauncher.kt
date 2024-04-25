@file:JvmName("HeadlessLauncher")

package ru.cardboard.kollizei.headless

import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import ru.cardboard.kollizei.Main

/** Launches the headless application. Can be converted into a server application or a scripting utility. */
fun main() {
    HeadlessApplication(Main(), HeadlessApplicationConfiguration().apply {
        // When this value is negative, Main#render() is never called:
        updatesPerSecond = -1
    })
}
