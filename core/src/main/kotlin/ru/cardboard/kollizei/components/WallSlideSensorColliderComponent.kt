package ru.cardboard.kollizei.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import ktx.ashley.mapperFor
import ktx.ashley.optionalPropertyFor

class WallSlideSensorColliderComponent : Component {
    companion object {
        val mapper = mapperFor<WallSlideSensorColliderComponent>()
    }

    var dir = 1f

}

var Entity.wallSlideSensor by optionalPropertyFor<WallSlideSensorColliderComponent>()
