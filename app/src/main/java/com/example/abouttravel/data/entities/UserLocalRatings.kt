package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "UserLocalRatings",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Local::class,
            parentColumns = ["id"],
            childColumns = ["local_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserLocalRatings(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @NonNull @ColumnInfo(name = "user_id") var userId: Int = 0,
    @NonNull @ColumnInfo(name = "local_id") var localId: Int = 0,
    @NonNull @ColumnInfo(name = "rating") var rating: Int = 0
) : Parcelable