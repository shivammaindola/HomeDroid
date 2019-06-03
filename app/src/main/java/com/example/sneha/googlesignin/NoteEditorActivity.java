package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import android.support.v7.widget.Toolbar;

import java.util.Collections;
import java.util.HashSet;

import me.anwarshahriar.calligrapher.Calligrapher;

public class NoteEditorActivity extends AppCompatActivity {
    EditText editText;
    int noteId;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_note_editor);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        editText = (EditText)findViewById(R.id.writehere);
        Intent intent = getIntent();
         toolbar=(Toolbar)findViewById(R.id.toolbar);
        noteId = intent.getIntExtra("noteId",-1);

        if(noteId!=(-1))
        {
            editText.setText(TodoNew.notes.get((noteId)));
        }
        else
        {
            TodoNew.notes.add("");
            noteId=TodoNew.notes.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TodoNew.notes.set(noteId,String.valueOf(s));
                TodoNew.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.sneha.googlesignin", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(TodoNew.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    }

