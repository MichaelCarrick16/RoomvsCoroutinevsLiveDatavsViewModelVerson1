package com.example.roomcoroutine

import com.example.roomcoroutine.database.Note

interface OnActionCallback {
    fun onCallback(note: Note)
}