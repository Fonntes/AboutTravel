package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "trips",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)


data class Trip(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "user_id") var userId: Int = 0,
    @NonNull @ColumnInfo(name = "label") var title: String = "",
    @NonNull @ColumnInfo(name = "country") var country: String = "",
    @NonNull @ColumnInfo(name = "location") var location: String = "",
    @NonNull @ColumnInfo(name = "date") var date: Date = Date(),
    @NonNull @ColumnInfo(name = "description") var description: String ="",
    @NonNull @ColumnInfo(name = "file_path") var image: String = "",
    @NonNull @ColumnInfo(name = "latitude") var latitude: String = "",
    @NonNull @ColumnInfo(name = "longitude") var longitude: String = "",
    @NonNull @ColumnInfo(name = "is_shared") var isShared: Boolean = false,
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "delete_at") var deleteAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date()
) : Parcelable