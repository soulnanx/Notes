package br.com.hivecode.notes.data.repository

import br.com.hivecode.notes.data.entity.Note

class NotesRepository {

    val noteList = mutableListOf<Note>(
        Note("take shower", false),
        Note("buy breakfast", true),
        Note("go work", true),
        Note("go home", true),
        Note("go to the gym", false)

    )

    fun fetchNotes() = noteList

    fun saveNote(note: Note) = noteList.add(note)
}