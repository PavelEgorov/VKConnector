package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomAudio

@Dao
interface AudioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(audio: RoomAudio)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg audio: RoomAudio)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(audios: List<RoomAudio>)

    @Update
    fun update(audio: RoomAudio)

    @Update
    fun update(vararg audio: RoomAudio)

    @Update
    fun update(audios: List<RoomAudio>)

    @Delete
    fun delete(audio: RoomAudio)

    @Delete
    fun delete(vararg audio: RoomAudio)

    @Delete
    fun delete(audios: List<RoomAudio>)

    @Query("SELECT * FROM RoomAudio WHERE wallId= :wallId")
    fun getAllWallAudio(wallId: Int): List<RoomAudio>

    @Query("SELECT * FROM RoomAudio WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallAudio(wallId: Int): RoomAudio?

    @Query("SELECT * FROM RoomAudio WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomAudio?
}