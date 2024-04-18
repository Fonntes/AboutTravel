package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(
    tableName = "locations",
    foreignKeys = [
        ForeignKey(
            entity = Trip::class,
            parentColumns = ["id"],
            childColumns = ["trip_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LocalType::class,
            parentColumns = ["id"],
            childColumns = ["local_type_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Rating::class,
            parentColumns = ["id"],
            childColumns = ["rating_id"],
            onDelete = ForeignKey.CASCADE
)
    ]
)
data class Location(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "trip_id") var tripId: Int = 0,
    @NonNull @ColumnInfo(name = "local_type_id") var localTypeId: Int = 0,
    @NonNull @ColumnInfo(name = "rating_id") var ratingId: Int = 0,
    @NonNull @ColumnInfo(name = "latitude") var latitude: String = "",
    @NonNull @ColumnInfo(name = "longitude") var longitude: String = "",
    @NonNull @ColumnInfo(name = "description") var description: String = "",
    @NonNull @ColumnInfo(name = "date") var date: Date = Date(),
    @NonNull @ColumnInfo(name = "rating") var rating: Int = 0,
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date(),
    @NonNull @ColumnInfo(name = "delete_at") var deleteAt: Date = Date()
) : Parcelable