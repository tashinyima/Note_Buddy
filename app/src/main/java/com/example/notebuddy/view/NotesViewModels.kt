package com.example.notebuddy.view

import androidx.lifecycle.*
import com.example.notebuddy.Repositories.NotesRepository
import com.example.notebuddy.myData.MyNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModels  @Inject constructor(private val notesRepository: NotesRepository):ViewModel() {

    lateinit var getnotesLiveData : LiveData<List<MyNotes>>
    fun loadNotes(){
        getnotesLiveData = liveData{
            emitSource(notesRepository.selectAllNotes().asLiveData())
        }
    }
    fun addNote(notes: MyNotes) {
        viewModelScope.launch {
            notesRepository.insertNote(notes)
        }
    }


    fun updateNote(notes: MyNotes){
        viewModelScope.launch {
            notesRepository.updateNote(notes)
        }
    }

    fun deleteNote(note: MyNotes){
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }

    fun deleteAll()  {
        viewModelScope.launch {
            notesRepository.deleteAll()
        }
    }


}