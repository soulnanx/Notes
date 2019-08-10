package br.com.hivecode.notes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hivecode.notes.R
import br.com.hivecode.notes.data.entity.Note
import kotlinx.android.synthetic.main.item_notes.view.*

class NotesViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bindView(item: Note){
        with(view){
            item_notes_title.text = item.title
            item_notes_image.setImageResource(
                if (item.isImportant)
                    R.drawable.ic_create_new_folder_black_24dp
                else
                    R.drawable.ic_drafts_black_24dp
            )
        }
    }
}

class NotesAdapter(private val list: MutableList<Note> = mutableListOf()) : RecyclerView.Adapter<NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun add(note: Note){
        list.add(note)
        notifyDataSetChanged()
    }

    fun add(notes: MutableList<Note>){
        list.clear()
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun remove(note: Note){
        list.remove(note)
        notifyDataSetChanged()
    }
}