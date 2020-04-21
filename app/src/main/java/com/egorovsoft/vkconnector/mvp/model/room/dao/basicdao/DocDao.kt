package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomDoc

@Dao
interface DocDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(doc: RoomDoc)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg doc: RoomDoc)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(docs: List<RoomDoc>)

    @Update
    fun update(doc: RoomDoc)

    @Update
    fun update(vararg doc: RoomDoc)

    @Update
    fun update(docs: List<RoomDoc>)

    @Delete
    fun delete(doc: RoomDoc)

    @Delete
    fun delete(vararg doc: RoomDoc)

    @Delete
    fun delete(doc: List<RoomDoc>)

    @Query("SELECT * FROM RoomDoc WHERE wallId= :wallId")
    fun getAllWallDoc(wallId: Int): List<RoomDoc>

    @Query("SELECT * FROM RoomDoc WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallDoc(wallId: Int): RoomDoc?

    @Query("SELECT * FROM RoomDoc WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomDoc?
}