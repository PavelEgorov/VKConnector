package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomLink

@Dao
interface LinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(link: RoomLink)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg link: RoomLink)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(links: List<RoomLink>)

    @Update
    fun update(link: RoomLink)

    @Update
    fun update(vararg link: RoomLink)

    @Update
    fun update(links: List<RoomLink>)

    @Delete
    fun delete(link: RoomLink)

    @Delete
    fun delete(vararg link: RoomLink)

    @Delete
    fun delete(links: List<RoomLink>)

    @Query("SELECT * FROM RoomLink WHERE wallId= :wallId")
    fun getAllWallLink(wallId: Int): List<RoomLink>

    @Query("SELECT * FROM RoomLink WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallLink(wallId: Int): RoomLink?

    @Query("SELECT * FROM RoomLink WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomLink?
}