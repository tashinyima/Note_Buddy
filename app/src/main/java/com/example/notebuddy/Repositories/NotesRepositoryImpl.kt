package com.example.notebuddy.Repositories

import com.example.notebuddy.Data.NotesDao
import com.example.notebuddy.myData.MyNotes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotesRepositoryImpl @Inject constructor(private val notesDao: NotesDao) : NotesRepository {


    override suspend fun insertNote(note: MyNotes) {
        return notesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: MyNotes) {
        return notesDao.deleteNote(note)
    }

    override suspend fun selectNoteById(id: Int): MyNotes {
        return notesDao.selectNoteById(id)
    }

    override suspend fun selectAllNotes(): Flow<List<MyNotes>> {
        return notesDao.selectAllNotes()
    }

    override suspend fun updateNote(note: MyNotes) {
        return notesDao.updateNote(note)
    }

    override suspend fun deleteAll() {
        return notesDao.deleteAll()
    }


}