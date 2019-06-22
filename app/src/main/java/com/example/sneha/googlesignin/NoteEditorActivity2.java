package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashSet;

import me.anwarshahriar.calligrapher.Calligrapher;

public class NoteEditorActivity2 extends AppCompatActivity {
    EditText editText;
    int StickyId;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_note_editor1);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this,R.style.Cambria);
        editText = (EditText)findViewById(R.id.writehere);
        Intent intent = getIntent();
        StickyId = intent.getIntExtra("PurchaseId",-1);

        if(StickyId!=(-1))
        {
            editText.setText(PurchaseList.notes.get((StickyId)));
            Toast.makeText(this, String.valueOf(StickyId), Toast.LENGTH_SHORT).show();
        }
        else
        {
            PurchaseList.notes.add("");
            StickyId=PurchaseList.notes.size()-1;
            Toast.makeText(this, String.valueOf(StickyId), Toast.LENGTH_SHORT).show();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!editText.equals("")){
                    PurchaseList.notes.set(StickyId,String.valueOf(s));
                    PurchaseList.arrayAdapter1.notifyDataSetChanged();

                    SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.sneha.googlesignin", Context.MODE_PRIVATE);
                    HashSet<String> set1 = new HashSet<>(PurchaseList.notes);
                    sharedPreferences1.edit().putStringSet("purchase",set1).apply();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

