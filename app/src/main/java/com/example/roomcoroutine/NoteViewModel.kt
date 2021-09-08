package com.example.roomcoroutine

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcoroutine.database.Note
import com.example.roomcoroutine.database.NoteDatabase
import com.example.roomcoroutine.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    private lateinit var context : Context
    private lateinit var repo : Repository
    lateinit var getAllNote : LiveData<List<Note>>

    fun setContext(context: Context){
        this.context = context
        val noteDao = NoteDatabase.getInstance(context).noteDao()
        repo = Repository(noteDao)
        this.getAllNote = repo.getAllNote
    }

    fun addData(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addData(note)
        }
    }

    fun deleteData(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteData(note)
        }
    }

}