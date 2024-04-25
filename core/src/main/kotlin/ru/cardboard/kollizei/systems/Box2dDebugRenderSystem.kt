package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World

class Box2dDebugRenderSystem(val world: World, var ppm: Float, val camera: OrthographicCamera) : EntitySystem() {

    val debugRenderer = Box2DDebugRenderer(true, true, false, true, true, true)

    override fun update(deltaTime: Float) {
        camera.position.x /= ppm
        camera.position.y /= ppm
        camera.zoom /= ppm
        camera.update()

        debugRenderer.render(world, camera.combined)

        camera.position.x *= ppm
        camera.position.y *= ppm
        camera.zoom *= ppm
        camera.update()
    }

}
