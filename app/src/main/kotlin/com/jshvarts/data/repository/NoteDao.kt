package com.jshvarts.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query
import com.jshvarts.data.model.Note
import com.jshvarts.data.model.NoteEntity
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: NoteEntity): Long

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("select * from notes where id = :id")
    fun findNoteById(id: Long): Note

    @Query("SELECT * FROM notes WHERE id = :id ORDER BY timestamp DESC")
    fun getAllNotes(id: Long): Flowable<List<NoteEntity>>
}