package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "trips")

data class Trip(
    @PrimaryKey var id: Int = 0,
    @NonNull @ColumnInfo(name = "user_id") var userId: Int = 0,
    @NonNull @ColumnInfo(name = "label") var label: String = "",
    @ColumnInfo(name = "country") var country_iso2: String? = null,
    @ColumnInfo(name = "location") var location: String? = null,
    @ColumnInfo(name = "initialDate") var initial_date: Date? = Date(),
    @ColumnInfo(name = "endDate") var end_date: Date? = Date(),
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "file_path") var image: String? = null,
    @ColumnInfo(name = "latitude") var latitude: String? = null,
    @ColumnInfo(name = "longitude") var longitude: String? = null,
    @NonNull @ColumnInfo(name = "is_shared") var isShared: Boolean = false,
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @ColumnInfo(name = "deleted_at") var deleteAt: Date? = null,
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date()
) : Parcelable