package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.PlayerControllerComponent
import ru.cardboard.kollizei.components.playerController

class UpdatePlayerControllersSystem : IteratingSystem(allOf(PlayerControllerComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        // TODO: device attached to player polling
        val playerContoller = entity.playerController
        if (playerContoller != null) {
            playerContoller.move = 0f
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) playerContoller.move -= 1f
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) playerContoller.move += 1f
            playerContoller.move = MathUtils.clamp(playerContoller.move, -1f, 1f)

            playerContoller.run = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
            playerContoller.jump = Gdx.input.isKeyPressed(Input.Keys.SPACE)
        }
    }

}
