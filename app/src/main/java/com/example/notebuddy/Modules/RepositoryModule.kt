package com.example.notebuddy.Modules

import com.example.notebuddy.Data.NotesDao
import com.example.notebuddy.Repositories.NotesRepository
import com.example.notebuddy.Repositories.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ViewModelComponent::class)

object RepositoryModule {

    @Provides
    fun provideNotesRepository(notesDao: NotesDao):NotesRepository {
        return NotesRepositoryImpl(notesDao)
    }

}