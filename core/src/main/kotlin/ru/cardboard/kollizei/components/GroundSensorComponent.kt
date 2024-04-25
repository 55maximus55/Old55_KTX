package ru.cardboard.kollizei.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class GroundSensorComponent : Component {
    companion object {
        val mapper = mapperFor<GroundSensorComponent>()
    }
}
