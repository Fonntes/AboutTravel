package com.example.abouttravel.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.abouttravel.data.dao.LocalTypeDao
import com.example.abouttravel.data.dao.LocationDao
import com.example.abouttravel.data.dao.MediaDao
import com.example.abouttravel.data.dao.TripDao
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.entities.Location
import com.example.abouttravel.data.entities.LocalType
import com.example.abouttravel.data.entities.Media

@Database(entities = [Trip::class, Location::class, LocalType::class, Media::class], version = 1)
@TypeConverters(Converters::class)
abstract class AboutTravelDataBase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun locationDao(): LocationDao
    abstract fun mediaDao(): MediaDao
    abstract fun localTypeDao(): LocalTypeDao

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
