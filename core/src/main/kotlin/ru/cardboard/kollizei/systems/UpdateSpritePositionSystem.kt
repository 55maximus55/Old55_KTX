package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.TransformComponent
import ru.cardboard.kollizei.components.spriteComponent
import ru.cardboard.kollizei.components.transform

class UpdateSpritePositionSystem : IteratingSystem(allOf(TransformComponent::class, TransformComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transform
        val sprite = entity.spriteComponent

        if (sprite != null && transform != null) {
            sprite.sprite.setCenter(transform.position.x, transform.position.y + 10f)
            sprite.sprite.setFlip(transform.dir < 0f, false)
        }
    }

}
