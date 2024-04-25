package ru.cardboard.kollizei.states.player

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.collections.gdxArrayOf
import ru.cardboard.kollizei.components.*

class JumpState : State<Entity> {
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
                    TextureRegion(Texture("players/1/jump/1.png")),
                    TextureRegion(Texture("players/1/jump/2.png"))
                )
            )

            jump.enabled = true
            move.enabled = true
        }
    }

    override fun update(entity: Entity) {
        val state = entity.state
        val jump = entity.jump
        val slide = entity.wallSlide

        if (state != null && jump != null) {
            if (jump.onGround) {
                state.stateMachine.changeState(IdleState())
            }
            if (slide != null && slide.onWall) state.stateMachine.changeState(WallSlideState())
        }
    }

    override fun exit(entity: Entity) {}
    override fun onMessage(entity: Entity, telegram: Telegram): Boolean {
        return false
    }
}
