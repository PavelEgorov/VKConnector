package com.egorovsoft.vkconnector.mvp.model.room.dao.basicdao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomNote

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: RoomNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg note: RoomNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: List<RoomNote>)

    @Update
    fun update(note: RoomNote)

    @Update
    fun update(vararg note: RoomNote)

    @Update
    fun update(notes: List<RoomNote>)

    @Delete
    fun delete(note: RoomNote)

    @Delete
    fun delete(vararg note: RoomNote)

    @Delete
    fun delete(note: List<RoomNote>)

    @Query("SELECT * FROM RoomNote WHERE wallId= :wallId")
    fun getAllWallNote(wallId: Int): List<RoomNote>

    @Query("SELECT * FROM RoomNote WHERE wallId= :wallId LIMIT 1")
    fun getFirstWallNote(wallId: Int): RoomNote?

    @Query("SELECT * FROM RoomNote WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomNote?
}