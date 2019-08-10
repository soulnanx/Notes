package br.com.hivecode.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hivecode.notes.data.entity.Note
import br.com.hivecode.notes.data.repository.NotesRepository

class NoteViewModel(val repository : NotesRepository) : ViewModel() {

    private val notes = MutableLiveData<List<Note>>()

    fun getNotes() : LiveData<List<Note>> {
        if (notes.value == null){
            loadNotes()
        }
        return notes
    }

    fun saveNote(note: Note){
        repository.saveNote(note)
        loadNotes()
    }

    fun loadNotes(){
        val notesFetched = repository.fetchNotes()
        notes.postValue(notesFetched)
    }
}