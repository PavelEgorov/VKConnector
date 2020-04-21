package com.egorovsoft.vkconnector.di.app.modules

import androidx.room.Room
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.room.db.MIGRATION_1_2
import com.egorovsoft.vkconnector.mvp.model.room.db.MIGRATION_2_3
import com.egorovsoft.vkconnector.ui.MainApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: MainApp): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

}