package ru.cardboard.kollizei

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen
import ktx.ashley.entity
import ktx.ashley.with
import ktx.box2d.createWorld
import ktx.math.vec2
import ru.cardboard.kollizei.components.SpriteComponent
import ru.cardboard.kollizei.entities.PlatformFactory
import ru.cardboard.kollizei.entities.PlayerFactory
import ru.cardboard.kollizei.systems.*

class GameScreen : KtxScreen {

    val batch = SpriteBatch()
    val camera = OrthographicCamera().apply {
        position.x = 960f
        position.y = 0f
        setToOrtho(false)
        update()
    }
    val viewport = ExtendViewport(1920f, 1080f, camera)

    val world = createWorld(gravity = vec2(0f, -16.667f), allowSleep = true).apply {
        setContactListener(Box2dContactListener())
    }
    val ppm = 60f // Pixel per Meter

    val platformFactory = PlatformFactory()
    val playerFactory = PlayerFactory()

    val engine = PooledEngine().apply {
        // Логика игроков
        addSystem(UpdatePlayerControllersSystem())
        addSystem(UpdateStateMachineSystem())
        // Движение
        addSystem(PlayerMoveSystem())
        addSystem(PlayerJumpSystem())
        // Обновление позиций
        addSystem(UpdateTransformByBox2dSystem(ppm))
        addSystem(UpdateAnimationsSystem())
        addSystem(UpdateSpritePositionSystem())
        // Отрисовка игры
        addSystem(RenderSystem(batch, camera))
        addSystem(Box2dDebugRenderSystem(world, ppm, camera))





        entity {
            with<SpriteComponent>().apply {
                sprite.setRegion(Texture("backdrop.png"))
                sprite.setSize(1920f, 1080f)
            }
        }
        platformFactory.createPlatform(this, world, ppm, 230f, 830f, 150f, 150f)
        platformFactory.createPlatform(this, world, ppm, 230f, 680f, 150f, 150f)
        platformFactory.createPlatform(this, world, ppm, 230f, 230f, 300f, 150f)
        platformFactory.createPlatform(this, world, ppm, 750f, 380f, 450f, 150f)
        platformFactory.createPlatform(this, world, ppm, 750f, 680f, 450f, 50f)
        platformFactory.createPlatform(this, world, ppm, 1355f, 230f, 300f, 150f)
        platformFactory.createPlatform(this, world, ppm, 1355f, 830f, 150f, 150f)
        platformFactory.createPlatform(this, world, ppm, 1355f, 680f, 150f, 150f)

        playerFactory.createPlayer(this, world, ppm, 800f, 1080f, 1)
    }

    override fun show() {}
    override fun hide() {}

    override fun render(delta: Float) {
        world.step(delta, 8, 3)
        engine.update(delta)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, false)
    }

    override fun pause() {}
    override fun resume() {}

    override fun dispose() {}
}
