package ru.cardboard.kollizei

import com.badlogic.gdx.Input
import com.strongjoshua.console.GUIConsole
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync
import ru.cardboard.kollizei.console.MyCommandExecutor

class Main : KtxGame<KtxScreen>() {

    lateinit var console: GUIConsole

    override fun create() {
        console = GUIConsole()
        console.window.titleLabel.setText("LOL")
        console.setCommandExecutor(MyCommandExecutor())
        console.setDisplayHiddenCommands(true)
        console.displayKeyID = Input.Keys.F12

        KtxAsync.initiate()

        addScreen(GameScreen())
        setScreen<GameScreen>()
    }

    override fun render() {
        super.render()
        console.draw()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        console.refresh()
    }

}
