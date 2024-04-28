package ru.cardboard.kollizei.states.player

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.collections.gdxArrayOf
import ru.cardboard.kollizei.components.*

class WallSlideState : State<Entity> {
    override fun enter(entity: Entity) {
        val animation = entity.animation
        val jump = entity.jump
        val move = entity.move

        if (animation != null && jump != null && move != null) {
            if (jump.onGround)
                animation.timer = 0f
            else
                animation.timer = 1f
            animation.animation = Animation<TextureRegion>(
                0.15f, gdxArrayOf(
                    TextureRegion(Texture("players/1/slide/1.png"))
                )
            )

            jump.enabled = true
            move.enabled = true
        }
    }

    override fun update(entity: Entity) {
        val state = entity.state
        val jump = entity.jump
        val wallSlide = entity.wallSlide
        val box2d = entity.box2d

        if (jump != null) jump.jumpCounter = 1
        if (wallSlide != null) {
            if (box2d != null && wallSlide.onWall) {
                val velocity = box2d.body.linearVelocity
                velocity.y = 0f
                box2d.body.linearVelocity = velocity
            }
        }
        if (state != null && wallSlide != null && !wallSlide.onWall) state.stateMachine.changeState(JumpState())
    }

    override fun exit(entity: Entity) {}
    override fun onMessage(entity: Entity, telegram: Telegram): Boolean {
        return false
    }
}
