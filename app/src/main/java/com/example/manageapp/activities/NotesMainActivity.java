package com.example.manageapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.manageapp.R;
import com.example.manageapp.adapters.NoteAdapter;
import com.example.manageapp.database.NoteDatabase;
import com.example.manageapp.entities.Note;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class NotesMainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    private RecyclerView notesRecyclerView;
    private List<Note> noteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(v -> startActivityForResult(
                new Intent(getApplicationContext(), CreateNotes.class),
                REQUEST_CODE_ADD_NOTE
        ));

        notesRecyclerView = findViewById(R.id.noteRecyclerView);
        notesRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList);
        notesRecyclerView.setAdapter(noteAdapter);

        getNote();
    }
    public void sendNote(View v){
        Intent in = new Intent(this, NotesMainActivity.class);
        startActivity(in);
    }

    private void getNote() {

        @SuppressLint("StaticFieldLeak")
        class GetNoteTask extends AsyncTask<Void, Void, List<Note>> {

            protected List<Note> doInBackground(Void... voids) {
                return NoteDatabase
                        .getNoteDatabase(getApplicationContext())
                        .noteDao().getAllNote();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if(noteList.size() == 0){
                    noteList.addAll(notes);
                    noteAdapter.notifyDataSetChanged();
                }else{
                    noteList.add(0, notes.get(0));
                    noteAdapter.notifyItemInserted(0);
                }
                notesRecyclerView.smoothScrollToPosition(0);
            }
        }
        new GetNoteTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            getNote();
        }
    }
}