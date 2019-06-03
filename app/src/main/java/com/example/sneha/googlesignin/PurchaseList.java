package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class PurchaseList extends AppCompatActivity {
    ListView listView;
    static ArrayAdapter arrayAdapter8;
    static ArrayList<String> notes1 = new ArrayList<>();
    SharedPreferences sharedPreferences5;
    Toolbar toolbar;

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
            case R.id.my_notees11 : startActivity(new Intent(PurchaseList.this,NoteEditorActivity2.class));
                return true;
            default: return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_purchase_list);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences5 = getApplicationContext().getSharedPreferences("com.example.sneha.googlesignin", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences5.getStringSet("purchase",null);

        listView = (ListView)findViewById(R.id.addnote);
        if(set==null) {

            notes1.add("Example list");
        }
        else
        {
            notes1 = new ArrayList(set);
        }
        arrayAdapter8 = new ArrayAdapter(PurchaseList.this,android.R.layout.simple_list_item_1, notes1);
        listView.setAdapter(arrayAdapter8);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PurchaseList.this,NoteEditorActivity2.class);
                intent.putExtra("PurchaseId",position);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                new android.app.AlertDialog.Builder(PurchaseList.this)
                        .setIcon(android.R.drawable.alert_light_frame)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really Want to delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notes1.remove(position);
                                arrayAdapter8.notifyDataSetChanged();
                                HashSet<String> set = new HashSet<>(TodoNew.notes);
                                sharedPreferences5.edit().putStringSet("purchase",set).apply();


                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }

    }

