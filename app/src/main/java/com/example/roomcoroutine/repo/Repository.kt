package com.example.roomcoroutine.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.roomcoroutine.database.Note
import com.example.roomcoroutine.database.NoteDao

class Repository(val dao : NoteDao) {

     fun addData(note: Note){
        dao.addData(note)
    }

    val getAllNote : LiveData<List<Note>> = dao.getAllNote()

    fun deleteData(note: Note){
        dao.deleteData(note)
    }
}