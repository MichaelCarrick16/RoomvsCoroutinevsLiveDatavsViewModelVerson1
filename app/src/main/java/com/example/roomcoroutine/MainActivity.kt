package com.example.roomcoroutine

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcoroutine.database.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnActionCallback {
    private lateinit var viewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initEvent()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.setContext(this)

        val noteAdapter = NoteAdapter()
        noteAdapter.setCallback(this)
        recycler_view.adapter = noteAdapter
        recycler_view.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        viewModel.getAllNote.observe(this, Observer {
            noteAdapter.setNewdata(it)
            Toast.makeText(this,"abc",Toast.LENGTH_SHORT).show()
        })

    }

    private fun initEvent() {
       fabAddNewNote.setOnClickListener {
           showAddNoteDialog()
       }
    }

    private fun showAddNoteDialog() {
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_new_note)
        dialog.setCancelable(false)

        var layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        var edtNoteTitle : EditText = dialog.findViewById(R.id.edt_note_title)
        var edtNoteDes : EditText = dialog.findViewById(R.id.edt_note_des)

        dialog.findViewById<TextView>(R.id.tv_note_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.tv_note_add).setOnClickListener {
            val note = Note(0,edtNoteTitle.text.toString(),edtNoteDes.text.toString())
            viewModel.addData(note)
            dialog.dismiss()
        }

        dialog.show()
        dialog.window!!.attributes = layoutParams


    }

    override fun onCallback(note: Note) {
        viewModel.deleteData(note)
    }


}