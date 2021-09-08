package com.example.roomcoroutine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object{
        private var instance : NoteDatabase? = null

        fun getInstance(context: Context) : NoteDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context,NoteDatabase::class.java,"NOTE_DATABASE")
                    .build()
            }
            return instance as NoteDatabase
        }
    }
}