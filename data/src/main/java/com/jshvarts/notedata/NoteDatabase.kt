package com.jshvarts.notedata

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

fun createNoteDao(context: Context): RoomNoteDao {
    return Room.databaseBuilder(context, NoteDatabase::class.java, "notesdb")
            .build().noteDao()
}

@Database(entities = arrayOf(NoteEntity::class), version = 1, exportSchema = false)
internal abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): RoomNoteDao
}