package com.example.abouttravel.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.abouttravel.data.dao.*
import com.example.abouttravel.data.entities.*

@Database(entities = [Trip::class, Local::class, LocalType::class, Media::class ,Session::class, UserTripShared::class, UserTripRatings::class, UserLocalRatings::class ], version = 1)
@TypeConverters(Converters::class)
abstract class AboutTravelDataBase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun locationDao(): LocationDao
    abstract fun mediaDao(): MediaDao
    abstract fun localTypeDao(): LocalTypeDao
    abstract fun sessionDao(): SessionDao
    abstract fun userTripSharedDao(): UserTripSharedDao
    abstract fun userTripRatingsDao(): UserTripRatingsDao
    abstract fun userLocalRatingsDao(): UserLocalRatingsDao

    companion object {
        @Volatile
        private var INSTANCE: AboutTravelDataBase? = null

        fun getDatabase(context: Context): AboutTravelDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AboutTravelDataBase::class.java,
                    "about_travel_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
