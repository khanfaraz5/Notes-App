package com.example.notesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.INotesRvAdapter
import com.example.notesapp.model.Note
import com.example.notesapp.adapter.NotesRVAdapter
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.R

class MainActivity : AppCompatActivity(), INotesRvAdapter {

    private lateinit var RV:RecyclerView
    private lateinit var input:EditText
    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV = findViewById(R.id.RV)
        input = findViewById(R.id.input)

        RV.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        RV.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun SubmitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_SHORT).show()
        }
    }

}