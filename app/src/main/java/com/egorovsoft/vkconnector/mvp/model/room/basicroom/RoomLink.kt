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
data class RoomLink (
    @PrimaryKey var url:String,
    var wallId: Int,
    var title: String,
    var description: String
)