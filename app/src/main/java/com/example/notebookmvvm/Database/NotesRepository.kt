package com.example.notebookmvvm.Database

import androidx.lifecycle.LiveData
import com.example.notebookmvvm.Models.Note

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note : Note) {
        noteDao.insertNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)


    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note.id , note.title , note.note)
    }
}