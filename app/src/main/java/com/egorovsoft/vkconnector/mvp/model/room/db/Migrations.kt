package com.egorovsoft.vkconnector.mvp.model.room.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE RoomUser (id Int NOT NULL Primary key, firstName TEXT NOT NULL, lastName TEXT NOT NULL, photo_50 TEXT NOT NULL)")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE RoomWall (id Int NOT NULL Primary key, ownerId Int NOT NULL, fromId Int NOT NULL, date Int NOT NULL, text TEXT NOT NULL, postType TEXT NOT NULL, replyOwnerid Int NOT NULL, replyPostId Int NOT NULL, FOREIGN KEY (fromId)  REFERENCES RoomUser (id) ON DELETE CASCADE, FOREIGN KEY (ownerId)  REFERENCES RoomUser (id) ON DELETE CASCADE) ")

        database.execSQL("CREATE TABLE RoomAudio (id Int NOT NULL Primary key, wallId Int NOT NULL, ownerId Int NOT NULL, artist TEXT NOT NULL, title TEXT NOT NULL, url TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomDoc (id Int NOT NULL Primary key, wallId Int NOT NULL, ownerId Int NOT NULL, title TEXT NOT NULL, url TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomLink (url TEXT NOT NULL Primary key, wallId Int NOT NULL, title TEXT NOT NULL, description TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomNote (id Int NOT NULL Primary key, wallId Int NOT NULL, ownerId Int NOT NULL, title TEXT NOT NULL, text TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomPhoto (id Int NOT NULL Primary key, wallId Int NOT NULL, albumId Int NOT NULL, ownerId Int NOT NULL, text TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomVideo (id Int NOT NULL Primary key, wallId Int NOT NULL, ownerId Int NOT NULL, title TEXT NOT NULL, description TEXT NOT NULL, player TEXT NOT NULL, FOREIGN KEY (wallId)  REFERENCES RoomWall (id) ON DELETE CASCADE)")
        database.execSQL("CREATE TABLE RoomSizes (url TEXT NOT NULL Primary key, photoId Int NOT NULL, type TEXT NOT NULL, FOREIGN KEY (photoId)  REFERENCES RoomPhoto (id) ON DELETE CASCADE)")
    }
}