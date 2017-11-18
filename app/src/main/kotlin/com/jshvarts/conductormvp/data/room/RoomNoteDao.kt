package com.jshvarts.conductormvp.data.room

import android.arch.persistence.room.*
import com.jshvarts.conductormvp.data.NoteDao
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface RoomNoteDao : NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findNoteById(id: Long): Maybe<NoteEntity>

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Single<List<NoteEntity>>
}