package ru.cardboard.kollizei.entities

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import ktx.ashley.entity
import ktx.ashley.with
import ktx.box2d.body
import ktx.box2d.box
import ru.cardboard.kollizei.components.*
import ru.cardboard.kollizei.states.player.IdleState

class PlayerFactory {

    fun createPlayer(engine: PooledEngine, world: World, ppm: Float, x: Float, y: Float, playerIndex: Int) {
        engine.entity {
            with<Box2dBodyComponent> {
                body = world.body {
                    type = BodyDef.BodyType.DynamicBody
                    position.set(x + 40f / 2f, y + 60f / 2f).scl(1f / ppm)
                    fixedRotation = true

                    box(width = 40f / ppm, height = 60f / ppm) {
                        friction = 0.3f
                        userData = this@entity.entity
                    }
                }
            }
            with<TransformComponent>()

            with<AnimationComponent>()
            with<SpriteComponent> {
                sprite.setSize(80f, 80f)
            }

            with<PlayerControllerComponent>()

            with<JumpComponent>()
            with<WallSlideComponent>()
            with<MoveComponent>()
            with<StateComponent> {
                stateMachine.owner = this@entity.entity
                stateMachine.changeState(IdleState())
            }


//            with<PlayerStateComponent> {
//                addState("Idle", PlayerIdleState())
//                addState("Walk", PlayerWalkState())
//                addState("Run", PlayerRunState())
//                addState("Jump", PlayerJumpState())
//
//                setState("Idle")
//            }
        }
    }

}
