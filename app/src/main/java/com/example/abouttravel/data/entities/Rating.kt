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
@Entity(tableName = "ratings",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Rating(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var Id: Int = 0,
    @NonNull @ColumnInfo(name = "rating_id") var ratingId: Int = 0,
    @NonNull @ColumnInfo(name = "user_id") var userId: Int = 0,
    @NonNull @ColumnInfo(name = "rating_type") var ratingType: String = "",
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date(),
    @NonNull @ColumnInfo(name = "deleted_at") var deletedAt: Date = Date()
) : Parcelable