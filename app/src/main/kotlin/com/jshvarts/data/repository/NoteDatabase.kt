package com.jshvarts.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.jshvarts.data.model.NoteEntity

@Database(entities = arrayOf(NoteEntity::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}