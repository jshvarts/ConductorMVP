package com.jshvarts.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query
import com.jshvarts.data.model.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findNoteById(id: Long): NoteEntity

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): List<NoteEntity>
}