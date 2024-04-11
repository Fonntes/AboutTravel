package com.example.abouttravel.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo(name = "name") var name: String = "",
    @NonNull @ColumnInfo(name = "token") var token: String = "",
    @NonNull @ColumnInfo(name = "phone_number") var phoneNumber: String = "",
    @NonNull @ColumnInfo(name = "email") var email: String = "",
    @NonNull @ColumnInfo(name = "file_path") var profileImage: String = "",
    @NonNull @ColumnInfo(name = "description") var description: String = "",
    @NonNull @ColumnInfo(name = "created_at") var createdAt: Date = Date(),
    @NonNull @ColumnInfo(name = "updated_at") var updatedAt: Date = Date()
) : Parcelable