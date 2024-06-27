package com.example.abouttravel.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.abouttravel.data.dao.LocalTypeDao
import com.example.abouttravel.data.dao.LocationDao
import com.example.abouttravel.data.dao.MediaDao
import com.example.abouttravel.data.dao.SessionDao
import com.example.abouttravel.data.dao.TripDao
import com.example.abouttravel.data.dao.UserLocalRatingsDao
import com.example.abouttravel.data.dao.UserTripRatingsDao
import com.example.abouttravel.data.dao.UserTripSharedDao
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.LocalType
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.entities.UserLocalRatings
import com.example.abouttravel.data.entities.UserTripRatings
import com.example.abouttravel.data.entities.UserTripShared

@Database(entities = [Trip::class, Local::class, LocalType::class, Media::class ,Session::class, UserTripShared::class, UserTripRatings::class, UserLocalRatings::class ], version = 3)
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
