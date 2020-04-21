package com.egorovsoft.vkconnector.mvp.model.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
        entity = RoomUser::class,
        parentColumns = ["id"],
        childColumns = ["fromId"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = RoomUser::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomWall (
    @PrimaryKey val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val postType: String,
    val replyOwnerid: Int,
    val replyPostId: Int
)