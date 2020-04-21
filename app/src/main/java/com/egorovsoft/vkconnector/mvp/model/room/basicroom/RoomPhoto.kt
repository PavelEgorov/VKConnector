package com.egorovsoft.vkconnector.mvp.model.room.basicroom

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.egorovsoft.vkconnector.mvp.model.room.RoomWall

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomWall::class,
        parentColumns = ["id"],
        childColumns = ["wallId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomPhoto (
    @PrimaryKey var id: Int,
    var wallId: Int,
    var albumId: Int,
    var ownerId: Int,
    var text: String
)