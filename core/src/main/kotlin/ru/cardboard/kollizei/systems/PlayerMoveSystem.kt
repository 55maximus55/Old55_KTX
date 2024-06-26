package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.*

class PlayerMoveSystem : IteratingSystem(allOf(Box2dBodyComponent::class, MoveComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val box2d = entity.box2d
        val move = entity.move
        val playerController = entity.playerController

        if (box2d != null && move != null && playerController != null) {
            if (move.enabled) {
                move.targetSpeed = playerController.move * if (playerController.run) move.moveRunSpeed else move.moveWalkSpeed

                val dt = if (deltaTime < MathUtils.FLOAT_ROUNDING_ERROR) 0.25f else deltaTime
                val mass = box2d.body.mass
                var acceleration = (move.targetSpeed - box2d.body.linearVelocity.x) / dt
                acceleration = MathUtils.clamp(acceleration, -move.maxAcceleration, move.maxAcceleration)
                box2d.body.applyForceToCenter(acceleration * mass, 0f, true)
            }
        }
    }

}
