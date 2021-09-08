package com.example.roomcoroutine.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDao {

    @Insert
    fun addData(note: Note)

    @Query("SELECT *FROM notes")
    fun getAllNote() : LiveData<List<Note>>

    @Delete
    fun deleteData(note: Note)



}