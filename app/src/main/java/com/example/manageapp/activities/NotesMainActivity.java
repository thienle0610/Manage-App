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
import com.example.manageapp.lists.NoteListsView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class NotesMainActivity extends AppCompatActivity implements NoteListsView {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;
    public static final int REQUEST_CODE_SHOW_NOTE = 3;
    private RecyclerView notesRecyclerView;
    private List<Note> noteList;
    private NoteAdapter noteAdapter;
    private int noteClickedPosition= -1;

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
        noteAdapter = new NoteAdapter(noteList, this);
        notesRecyclerView.setAdapter(noteAdapter);

        getNote(REQUEST_CODE_SHOW_NOTE);
    }

    @Override
    public void onNoteClicked(Note note, int position) {
        noteClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(), CreateNotes.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);

    }

    public void sendNote(View v){
        Intent in = new Intent(this, NotesMainActivity.class);
        startActivity(in);
    }

    private void getNote(final int requestCode) {

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
                if(requestCode == REQUEST_CODE_SHOW_NOTE){
                    noteList.addAll(notes);
                    noteAdapter.notifyDataSetChanged();
                }else if(requestCode == REQUEST_CODE_ADD_NOTE){
                    noteList.add(0, notes.get(0));
                    noteAdapter.notifyItemInserted(0);
                    notesRecyclerView.smoothScrollToPosition(0);
                }else if(requestCode == REQUEST_CODE_UPDATE_NOTE){
                    noteList.remove(noteClickedPosition);
                    noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                    noteAdapter.notifyItemChanged(noteClickedPosition);
                }
            }
        }
        new GetNoteTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            getNote(REQUEST_CODE_ADD_NOTE);
        }else if(requestCode==REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK){
            if(data!= null){
                getNote(REQUEST_CODE_UPDATE_NOTE);
            }
        }
    }
}