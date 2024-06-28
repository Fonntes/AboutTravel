package com.example.abouttravel.data.converters

import androidx.room.TypeConverter
import com.example.abouttravel.data.entities.Local
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Date

object Converters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromLocalList(value: List<Local>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toLocalList(value: String?): List<Local>? {
        if (value == null) {
            return emptyList()
        }

        val listType: Type = object : TypeToken<List<Local>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    // Adicione conversores semelhantes para outras entidades como Media, etc.
}
