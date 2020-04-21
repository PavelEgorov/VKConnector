package com.egorovsoft.vkconnector.mvp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser (
    @PrimaryKey var id: Int,
    var firstName: String,
    var lastName: String,
    var photo_50: String
)