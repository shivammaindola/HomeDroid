package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

import me.anwarshahriar.calligrapher.Calligrapher;

public class TodoNew extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    static ArrayAdapter arrayAdapter1;
    static ArrayList <String> notes = new ArrayList<>();
    SharedPreferences sharedPreferences1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass1, menu);

        return super.onCreateOptionsMenu(menu);
    } @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees11 : startActivity(new Intent(TodoNew.this,NoteEditorActivity.class));
                return true;
            default: return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_sticky_notes);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this,R.style.Cambria);
        sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.sneha.googlesignin", Context.MODE_PRIVATE);
        HashSet<String> set1 = (HashSet<String>) sharedPreferences1.getStringSet("todo",null);

        listView = (ListView)findViewById(R.id.addnotes1);
        if(set1 == null) {

            notes.add("Example notes.....");
            //Toast.makeText(this, notes.get(0), Toast.LENGTH_SHORT).show();
            set1 = new HashSet<String>(notes);
            sharedPreferences1.edit().putStringSet("todo",set1).apply();

        }
        else
        {
            notes = new ArrayList(set1);
        }
        arrayAdapter1 = new ArrayAdapter(TodoNew.this,android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TodoNew.this,NoteEditorActivity.class);
                intent.putExtra("noteId",position);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                new android.app.AlertDialog.Builder(TodoNew.this)
                        .setIcon(android.R.drawable.alert_light_frame)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really Want to delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notes.remove(position);
                                arrayAdapter1.notifyDataSetChanged();
                                HashSet<String> set1 = new HashSet<>(TodoNew.notes);
                                sharedPreferences1.edit().putStringSet("todo",set1).apply();


                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }
}

