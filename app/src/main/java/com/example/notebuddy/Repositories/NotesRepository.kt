package com.example.notebuddy.Repositories


import com.example.notebuddy.myData.MyNotes
import kotlinx.coroutines.flow.Flow


interface NotesRepository {

    suspend fun insertNote(note: MyNotes)
    suspend fun deleteNote(notes: MyNotes)
    suspend fun selectNoteById(id: Int): MyNotes
    suspend fun selectAllNotes(): Flow<List<MyNotes>>
    suspend fun updateNote(note: MyNotes)
    suspend fun deleteAll()




}