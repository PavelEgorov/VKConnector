package com.egorovsoft.vkconnector.mvp.model.room.dao

import androidx.room.*
import com.egorovsoft.vkconnector.mvp.model.room.RoomCurrentSessionInfo

@Dao
interface CurrentSessionInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentSessionInfo: RoomCurrentSessionInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg currentSessionInfo: RoomCurrentSessionInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentSessionsInfo: List<RoomCurrentSessionInfo>)

    @Update
    fun update(currentSessionInfo: RoomCurrentSessionInfo)

    @Update
    fun update(vararg currentSessionInfo: RoomCurrentSessionInfo)

    @Update
    fun update(currentSessionsInfo: List<RoomCurrentSessionInfo>)

    @Delete
    fun delete(currentSessionInfo: RoomCurrentSessionInfo)

    @Delete
    fun delete(vararg currentSessionInfo: RoomCurrentSessionInfo)

    @Delete
    fun delete(currentSessionsInfo: List<RoomCurrentSessionInfo>)

    @Query("SELECT * FROM RoomCurrentSessionInfo")
    fun getAllSessions(): List<RoomCurrentSessionInfo>

    /// Сейчас у нас всегда будет одна запись, но на всякий случай (возможно в будущем пересмотрю механизм) делаю выборку первого.
    @Query("SELECT * FROM RoomCurrentSessionInfo ORDER BY date DESC LIMIT 1")
    fun getCurrentSession(): RoomCurrentSessionInfo?
}