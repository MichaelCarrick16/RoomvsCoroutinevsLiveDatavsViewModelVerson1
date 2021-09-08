package com.example.roomcoroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcoroutine.database.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private var listNotes = emptyList<Note>()
    private lateinit var callback : OnActionCallback

    fun setNewdata(list: List<Note>){
        this.listNotes = list
        notifyDataSetChanged()
    }

    fun setCallback(callback: OnActionCallback){
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notes,parent,false))
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        var note = listNotes.get(position)
        holder.tvItemTitle.setText(note.noteTitle)
        holder.tvItemDes.setText(note.noteDes)

        holder.imvDelete.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                callback.onCallback(note)
            }

        })
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvItemTitle : TextView = itemView.findViewById(R.id.tv_item_title)
        var tvItemDes : TextView = itemView.findViewById(R.id.tv_item_des)
        var imvDelete : ImageView = itemView.findViewById(R.id.imv_delete)
    }
}