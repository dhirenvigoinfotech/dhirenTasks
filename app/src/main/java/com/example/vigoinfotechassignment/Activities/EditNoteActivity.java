package com.example.vigoinfotechassignment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vigoinfotechassignment.Helper.DatabaseHelper;
import com.example.vigoinfotechassignment.Model.Note;
import com.example.vigoinfotechassignment.R;

public class EditNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private DatabaseHelper databaseHelper;
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEditText = findViewById(R.id.editTextTitle);
        contentEditText = findViewById(R.id.editTextContent);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("note_id")) {
            long noteId = intent.getLongExtra("note_id", -1);
            currentNote = databaseHelper.getNoteById(noteId);

            titleEditText.setText(currentNote.getTitle());
            contentEditText.setText(currentNote.getContent());
        }
    }

    public void saveNote(View view) {
        String updatedTitle = titleEditText.getText().toString().trim();
        String updatedContent = contentEditText.getText().toString().trim();

        if (!updatedTitle.isEmpty() && !updatedContent.isEmpty()) {
            currentNote.setTitle(updatedTitle);
            currentNote.setContent(updatedContent);

            int rowsAffected = databaseHelper.updateNote(currentNote);

            if (rowsAffected > 0) {
                Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update note", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}

