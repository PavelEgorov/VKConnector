package com.egorovsoft.vkconnector.mvp.model.room.basicroom

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomPhoto

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomPhoto::class,
        parentColumns = ["id"],
        childColumns = ["photoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomSizes (
    @PrimaryKey var url: String,
    var photoId: Int,
    var type: String
)