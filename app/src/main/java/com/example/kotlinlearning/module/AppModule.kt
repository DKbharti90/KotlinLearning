package com.example.kotlinlearning.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase{
        /*
        // If Migration is Required
        return Room.databaseBuilder(
             appContext,
             AppDatabase::class.java,
             AppConstant.DATABASE_NAME)
             .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()*/

        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppConstant.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: AppDatabase) = db.moduleDao()


    // If Migration is used
    //Migration(1, 2) 1,2 will come form dara base version maintained on Database class

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }

    private val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
        }
    }
}