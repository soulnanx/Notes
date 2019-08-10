package br.com.hivecode.notes.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.hivecode.notes.R
import br.com.hivecode.notes.data.entity.Note
import br.com.hivecode.notes.ui.adapter.NotesAdapter
import br.com.hivecode.notes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_new_note.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val notesViewModel: NoteViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setAdapter()
//        setViewModel()
        setNotesObserver()
    }

    private fun setNotesObserver() {
        notesViewModel.getNotes().observe(
            this,
            Observer {
                notesAdapter.add(it.toMutableList())
            })
    }

//    without dependency injection (koin)
//    private fun setViewModel() {
//        notesViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
//    }

    private fun setAdapter() {
        recycler_view_notes.adapter = notesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.new_item) {
            showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_new_note, null, false)
        val alertDialog = AlertDialog.Builder(this).setView(layout)

        with(alertDialog){
            setPositiveButton("create") { dialog, _ -> saveNote(loadValuesFrom(layout), dialog, layout) }
            setNegativeButton("cancel", null)
            setCancelable(false)
            setTitle("Do you want to create a new note?")
            create().show()
        }

    }

    private fun loadValuesFrom(layout: View): Note {
        return Note(
            layout.dialog_new_note_title.text.toString(),
            layout.dialog_new_note_important.isChecked
        )
    }

    private fun saveNote(note: Note, dialog: DialogInterface, layout: View) {
       if (note.title.isNullOrEmpty()){
           layout.dialog_new_note_title.error = "this field is mandatory!"
       } else {
           notesViewModel.saveNote(note)
           dialog.dismiss()
           Toast.makeText(this, "A new note was cteated", Toast.LENGTH_SHORT).show()
       }
    }
}
