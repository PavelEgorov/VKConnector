package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomVideo

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: RoomVideo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg video: RoomVideo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(videos: List<RoomVideo>)

    @Update
    fun update(video: RoomVideo)

    @Update
    fun update(vararg video: RoomVideo)

    @Update
    fun update(videos: List<RoomVideo>)

    @Delete
    fun delete(video: RoomVideo)

    @Delete
    fun delete(vararg video: RoomVideo)

    @Delete
    fun delete(videos: List<RoomVideo>)

    @Query("SELECT * FROM RoomVideo WHERE wallId= :wallId")
    fun getAllWallVideo(wallId: Int): List<RoomVideo>

    @Query("SELECT * FROM RoomVideo WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallVideo(wallId: Int): RoomVideo?

    @Query("SELECT * FROM RoomVideo WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomVideo?
}