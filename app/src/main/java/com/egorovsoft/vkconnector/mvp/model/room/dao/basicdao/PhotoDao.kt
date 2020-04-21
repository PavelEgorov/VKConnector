package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomPhoto

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photo: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RoomPhoto>)

    @Update
    fun update(photo: RoomPhoto)

    @Update
    fun update(vararg photo: RoomPhoto)

    @Update
    fun update(photos: List<RoomPhoto>)

    @Delete
    fun delete(photo: RoomPhoto)

    @Delete
    fun delete(vararg photo: RoomPhoto)

    @Delete
    fun delete(photos: List<RoomPhoto>)

    @Query("SELECT * FROM RoomPhoto WHERE wallId= :wallId")
    fun getAllWallPhoto(wallId: Int): List<RoomPhoto>

    @Query("SELECT * FROM RoomPhoto WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallPhoto(wallId: Int): RoomPhoto?

    @Query("SELECT * FROM RoomPhoto WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomPhoto?
}