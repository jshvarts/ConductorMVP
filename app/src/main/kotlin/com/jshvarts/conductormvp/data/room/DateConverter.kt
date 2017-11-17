package com.jshvarts.conductormvp.data.room

import android.arch.persistence.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}