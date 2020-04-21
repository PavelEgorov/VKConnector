package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomSizes

@Dao
interface SizesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(size: RoomSizes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photo: RoomSizes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sizes: List<RoomSizes>)

    @Update
    fun update(size: RoomSizes)

    @Update
    fun update(vararg size: RoomSizes)

    @Update
    fun update(sizes: List<RoomSizes>)

    @Delete
    fun delete(size: RoomSizes)

    @Delete
    fun delete(vararg size: RoomSizes)

    @Delete
    fun delete(sizes: List<RoomSizes>)

    @Query("SELECT * FROM RoomSizes WHERE photoId= :photoId")
    fun getAllPhotoSizes(photoId: Int): List<RoomSizes>

    @Query("SELECT * FROM RoomSizes WHERE photoId= :photoId AND type = 'm' LIMIT 1")
    fun getMPhotoSize(photoId: Int): RoomSizes?

    @Query("SELECT * FROM RoomSizes WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomSizes?
}