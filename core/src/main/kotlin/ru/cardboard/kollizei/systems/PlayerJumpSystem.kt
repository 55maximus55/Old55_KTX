package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.*

class PlayerJumpSystem : IteratingSystem(allOf(PlayerControllerComponent::class, JumpComponent::class, Box2dBodyComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val jump = entity.jump
        val playerController = entity.playerController
        val box2d = entity.box2d
        val wallSlide = entity.wallSlide

        if (playerController != null && jump != null && box2d != null) {
            if (jump.enabled) {
                if (playerController.jump) {
                    if (!jump.isJumping && jump.jumpCounter < jump.maxJumpCount) {
                        val velocity = box2d.body.linearVelocity
                        velocity.y = 700f / 60f
                        if (wallSlide != null && wallSlide.onWall) {
                            velocity.x += 400f / 60f * wallSlide.dir
                            wallSlide.onWall = false
                        }
                        box2d.body.linearVelocity = velocity

                        jump.isJumping = true
                        jump.jumpCounter++
                    }
                } else {
                    if (jump.isJumping) {
                        val velocity = box2d.body.linearVelocity
                        if (velocity.y > 0) velocity.y = 10f / 60f
                        box2d.body.linearVelocity = velocity
                        jump.isJumping = false
                    }
                }
            }
        }
    }

}
