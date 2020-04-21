package com.egorovsoft.vkconnector.mvp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RoomCurrentSessionInfo (
    /// При такой расстановке у мен всегда будет одна запись, т.к. приложение в ВК имеет всего один id.
    /// Но в дальнейшем я возможно пересмотрю данный механизм.
    @PrimaryKey var appId: Int,
    var userId: Int,
    var currentToken: String,
    var date: Int
)