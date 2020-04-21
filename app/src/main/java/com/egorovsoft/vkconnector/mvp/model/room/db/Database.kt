package com.egorovsoft.vkconnector.mvp.model.room.db

import androidx.room.RoomDatabase
import com.egorovsoft.vkconnector.mvp.model.room.RoomCurrentSessionInfo
import com.egorovsoft.vkconnector.mvp.model.room.RoomUser
import com.egorovsoft.vkconnector.mvp.model.room.RoomWall
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.*
import com.egorovsoft.vkconnector.mvp.model.room.dao.CurrentSessionInfoDao
import com.egorovsoft.vkconnector.mvp.model.room.dao.UserDao
import com.egorovsoft.vkconnector.mvp.model.room.dao.WallDao
import com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao.*

@androidx.room.Database(
    entities = [
        RoomCurrentSessionInfo::class,
        RoomUser::class,
        RoomWall::class,
        RoomAudio::class,
        RoomDoc::class,
        RoomLink::class,
        RoomPhoto::class,
        RoomSizes::class,
        RoomVideo::class,
        RoomNote::class
    ],
    version = 3
)
abstract class Database: RoomDatabase() {
    abstract val currentSessionInfoDao: CurrentSessionInfoDao
    abstract val userDao: UserDao
    abstract val wallDao: WallDao
    abstract val audioDao: AudioDao
    abstract val docDao: DocDao
    abstract val linkDao: LinkDao
    abstract val photoDao: PhotoDao
    abstract val sizesDao: SizesDao
    abstract val videoDao: VideoDao
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "database.db"
    }
}