package ru.cardboard.kollizei.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ru.cardboard.kollizei.components.state

class UpdateStateMachineSystem : IteratingSystem(allOf().get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        entity.state?.stateMachine?.update()
    }

}
