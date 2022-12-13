package com.example.notebuddy.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notebuddy.Data.NoteDatabase.Companion.DATABASE_VERSION
import com.example.notebuddy.myData.MyNotes

@Database(
    entities = [MyNotes::class],
    version = DATABASE_VERSION,
    exportSchema = false
)

abstract class NoteDatabase : RoomDatabase(){
    abstract fun notesDao(): NotesDao

    companion object{
        const val DATABASE_VERSION =3
        const val DATABASE_NAME ="Notes_Buddy_DB"
    }

}