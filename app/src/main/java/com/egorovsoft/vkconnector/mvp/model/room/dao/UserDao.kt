package com.egorovsoft.vkconnector.mvp.model.room.dao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.RoomUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(vararg user: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(vararg user: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    @Query("SELECT * FROM RoomUser ")
    fun getAllUsers(): List<RoomUser>

    /// Сейчас у нас всегда будет одна запись, но на всякий случай (возможно в будущем пересмотрю механизм) делаю выборку первого.
    @Query("SELECT * FROM RoomUser WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomUser?
}