package com.example.notebuddy.Data.Module

import android.content.Context
import androidx.room.Room
import com.example.notebuddy.Data.NoteDatabase
import com.example.notebuddy.Data.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun provideNotesDao(database: NoteDatabase): NotesDao {
        return database.notesDao()
    }
}