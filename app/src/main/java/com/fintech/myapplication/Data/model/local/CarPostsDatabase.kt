package com.fintech.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class],
    version = DatabaseMigrations.DB_VERSION,
    exportSchema = false,
)
abstract class CarPostsDatabase : RoomDatabase() {
    /**
     * @return [PostsDao] CAR Posts Data Access Object.
     */
    abstract fun getPostsDao(): PostsDao

    companion object {
        const val DB_NAME = "car_database"

        @Suppress("ktlint:standard:property-naming")
        @Volatile
        private var INSTANCE: CarPostsDatabase? = null

        fun getInstance(context: Context): CarPostsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            CarPostsDatabase::class.java,
                            DB_NAME,
                        ).addMigrations(*DatabaseMigrations.MIGRATIONS)
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
