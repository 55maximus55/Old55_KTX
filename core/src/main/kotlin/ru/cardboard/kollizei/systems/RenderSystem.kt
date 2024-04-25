package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.ashley.allOf
import ktx.graphics.use
import ru.cardboard.kollizei.components.SpriteComponent
import ru.cardboard.kollizei.components.spriteComponent

class RenderSystem(val batch: SpriteBatch, val camera: OrthographicCamera) : EntitySystem() {

    val family = allOf(SpriteComponent::class).get()

    override fun update(deltaTime: Float) {
        batch.use(camera) {
            for (entity in engine.getEntitiesFor(family)) {
                entity.spriteComponent?.sprite?.draw(batch)
            }
        }
    }

}
