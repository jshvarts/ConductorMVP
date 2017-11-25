package com.jshvarts.notedata

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Room-specific Note Entity data type.
 */
@Entity(tableName = "notes")
data class NoteEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "note_text") val noteText: String,
        @ColumnInfo(name = "timestamp") val timestamp: Long)

