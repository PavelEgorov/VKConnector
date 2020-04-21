package com.egorovsoft.vkconnector.mvp.model.room.dao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.RoomWall

@Dao
interface WallDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wall: RoomWall)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg wall: RoomWall)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(walls: List<RoomWall>)

    @Update
    fun update(wall: RoomWall)

    @Update
    fun update(vararg wall: RoomWall)

    @Update
    fun update(walls: List<RoomWall>)

    @Delete
    fun delete(wall: RoomWall)

    @Delete
    fun delete(vararg wall: RoomWall)

    @Delete
    fun delete(walls: List<RoomWall>)

    @Query("SELECT * FROM RoomWall WHERE ownerId= :userId ORDER BY date DESC")
    fun getUserWall(userId: Int): List<RoomWall>

    @Query("SELECT * FROM RoomWall WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomWall?
}