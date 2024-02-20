package com.example.vigoinfotechassignment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vigoinfotechassignment.Helper.DatabaseHelper;
import com.example.vigoinfotechassignment.Model.Note;
import com.example.vigoinfotechassignment.Adapter.NoteAdapter;
import com.example.vigoinfotechassignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        recyclerView = findViewById(R.id.recyclerView);
        noteList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        noteList.addAll(databaseHelper.getAllNotes());

        noteAdapter = new NoteAdapter(noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Note selectedNote = noteList.get(position);
                Intent intent = new Intent(NoteListActivity.this, EditNoteActivity.class);
                intent.putExtra("note_id", selectedNote.getId());
                startActivity(intent);
            }
        });


        noteAdapter.setOnItemDeleteListener(new NoteAdapter.OnItemDeleteListener() {
            @Override
            public void onItemDelete(int position) {
                deleteNote(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabAddNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, AddNoteActivity.class));
            }
        });
    }

    private void deleteNote(int position) {
        Note noteToDelete = noteList.get(position);
        long noteId = noteToDelete.getId();
        databaseHelper.deleteNote(noteId);
        noteList.remove(position);
        noteAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteList.clear();
        noteList.addAll(databaseHelper.getAllNotes());
        noteAdapter.notifyDataSetChanged();
    }
}
