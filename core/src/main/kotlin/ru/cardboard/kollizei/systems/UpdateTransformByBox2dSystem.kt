package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.Box2dBodyComponent
import ru.cardboard.kollizei.components.TransformComponent
import ru.cardboard.kollizei.components.box2d
import ru.cardboard.kollizei.components.transform

class UpdateTransformByBox2dSystem(val ppm: Float) : IteratingSystem(allOf(Box2dBodyComponent::class, TransformComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transform
        val box2d = entity.box2d

        if (box2d != null && transform != null) {
            transform.position.set(box2d.body.position).scl(ppm)
            if (box2d.body.linearVelocity.x > 0.1f) transform.dir = 1f
            if (box2d.body.linearVelocity.x < -0.1f) transform.dir = -1f
        }
    }

}
