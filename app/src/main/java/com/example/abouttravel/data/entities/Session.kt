package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "phone_number") var phoneNumber: String? = "",
    @NonNull @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "file_path") var profilePicture: String? = "",
    @ColumnInfo(name = "description") var description: String? = "",
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date(),
    @ColumnInfo(name = "deleted_at") var deleteAt: Date? = null
) : Parcelable