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
@Entity(
    tableName = "media",
    foreignKeys = [
        ForeignKey(
            entity = Local::class,
            parentColumns = ["id"],
            childColumns = ["location_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Media(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "location_id") var locationId: Int = 0,
    @NonNull @ColumnInfo(name = "label") var label: String = "",
    @NonNull @ColumnInfo(name = "path") var path: String = "",
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date(),
    @ColumnInfo(name = "deleted_at") var deleteAt: Date? = null
) : Parcelable