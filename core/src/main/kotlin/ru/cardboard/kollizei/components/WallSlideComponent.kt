package ru.cardboard.kollizei.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import ktx.ashley.mapperFor
import ktx.ashley.optionalPropertyFor

class WallSlideComponent : Component {
    companion object {
        val mapper = mapperFor<WallSlideComponent>()
    }

    var dir = 1f
    var onWall = false


}

var Entity.wallSlide by optionalPropertyFor<WallSlideComponent>()
