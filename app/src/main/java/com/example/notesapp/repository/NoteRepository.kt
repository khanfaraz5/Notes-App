package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.model.Note
import com.example.notesapp.db.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}