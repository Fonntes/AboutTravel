package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "local_types")
data class LocalType(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "label") var label: String = "",
    @NonNull @ColumnInfo(name = "description") var description: String = "",
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date()
) : Parcelable