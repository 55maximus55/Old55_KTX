package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.AnimationComponent
import ru.cardboard.kollizei.components.animation
import ru.cardboard.kollizei.components.spriteComponent

class UpdateAnimationsSystem : IteratingSystem(allOf(AnimationComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animation = entity.animation
        val sprite = entity.spriteComponent
        if (animation != null && sprite != null) {
            animation.timer += deltaTime
            sprite.sprite.setRegion(animation.animation.getKeyFrame(animation.timer))
        }
    }

}
