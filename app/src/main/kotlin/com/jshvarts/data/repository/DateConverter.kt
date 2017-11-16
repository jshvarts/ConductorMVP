package com.jshvarts.data.repository

import android.arch.persistence.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}