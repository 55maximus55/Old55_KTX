package ru.cardboard.kollizei.entities

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import ktx.ashley.entity
import ktx.ashley.with
import ktx.box2d.body
import ktx.box2d.box
import ktx.math.vec2
import ru.cardboard.kollizei.components.Box2dBodyComponent
import ru.cardboard.kollizei.components.GroundSensorComponent
import ru.cardboard.kollizei.components.SpriteComponent
import ru.cardboard.kollizei.components.WallSlideSensorColliderComponent

class PlatformFactory {

    val textures = Array<Texture>().apply {
        for (i in 1..10) {
            add(Texture("tiles/$i.png"))
        }
    }

    fun createPlatform(engine: PooledEngine, world: World, ppm: Float, x: Float, y: Float, width: Float, height: Float) {
        // Platform body
        engine.entity {
            with<SpriteComponent> {
                sprite.apply {
                    texture = textures.random()
                    setRegion(texture)
                    setSize(width, height)
                    setCenter(x, y)
                }
            }
            with<Box2dBodyComponent> {
                body = world.body {
                    type = BodyDef.BodyType.StaticBody
                    position.set(x, y).scl(1f / ppm)

                    box(width = width / ppm, height = height / ppm) {
                        friction = 0f
                        userData = this@entity.entity
                    }
                }
            }
        }
        // Ground sensor
        engine.entity {
            with<Box2dBodyComponent> {
                body = world.body {
                    type = BodyDef.BodyType.StaticBody
                    position.set(x, y).scl(1f / ppm)

                    box(width = (width - 1f) / ppm, 1f / ppm, position = vec2(0f, height / 2f / ppm)) {
                        isSensor = true
                        userData = this@entity.entity
                    }
//                    box(width = 8f / ppm, height = height / 4f / ppm, position = vec2(-width / ppm / 2f)) {
//                        isSensor = true
//                    }
//                    box(width = 8f / ppm, height = height / 4f / ppm, position = vec2(width / ppm / 2f)) {
//                        isSensor = true
//                    }
                }
            }
            with<GroundSensorComponent>()
        }
        // Left wall sensor
        engine.entity {
            with<Box2dBodyComponent> {
                body = world.body {
                    type = BodyDef.BodyType.StaticBody
                    position.set(x, y).scl(1f / ppm)

                    box(width = 3f / ppm, height = height / 4f / ppm, position = vec2(-width / ppm / 2f)) {
                        isSensor = true
                        userData = this@entity.entity
                    }
                }
            }
            with<WallSlideSensorColliderComponent> {
                dir = -1f
            }
        }
        // Left wall sensor
        engine.entity {
            with<Box2dBodyComponent> {
                body = world.body {
                    type = BodyDef.BodyType.StaticBody
                    position.set(x, y).scl(1f / ppm)

                    box(width = 3f / ppm, height = height / 4f / ppm, position = vec2(width / ppm / 2f)) {
                        isSensor = true
                        userData = this@entity.entity
                    }
                }
            }
            with<WallSlideSensorColliderComponent> {
                dir = 1f
            }
        }
    }

}
