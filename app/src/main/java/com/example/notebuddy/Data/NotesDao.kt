package com.example.notebuddy.Data

import androidx.room.*
import com.example.notebuddy.myData.MyNotes


import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Query("SELECT*  FROM NotesTable ORDER BY id DESC")
    fun selectAllNotes(): Flow<List<MyNotes>>

    @Query("SELECT * FROM NotesTable WHERE id=:id")
    fun selectNoteById(id: Int): MyNotes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: MyNotes)


    @Update
    suspend fun updateNote(note: MyNotes)

    @Delete
    suspend fun deleteNote(note: MyNotes)

    @Query("DELETE FROM NotesTable")
    suspend fun deleteAll()




}