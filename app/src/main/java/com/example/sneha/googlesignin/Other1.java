package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class Other1 extends AppCompatActivity {

    MaterialSearchView searchView;
    DatabaseHelper11 database;
    ArrayList<String> arrayQuestion;
    ArrayList<String> arrayAnswer;
    ArrayList<String> arrayForm;
    RecyclerView allList;
    AdapterClassOther adapter;

    // MenuBar...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass1, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    // Clicking on add button....
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees11:
                Intent intent = new Intent(Other1.this, Other.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other1);

        // Initialization...
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        database = new DatabaseHelper11(this);
        arrayAnswer = new ArrayList<>();
        arrayQuestion = new ArrayList<>();
        arrayForm = new ArrayList<>();
        allList = findViewById(R.id.AllList);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        // Setting toolbar..
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this, R.style.Cambria);

        // Setting the recycler View....
        allList.setHasFixedSize(true);
        allList.setLayoutManager(new LinearLayoutManager(this));
        setAdapter();

    }

    private void setAdapter() {
        Cursor res = database.getAllData();

        // Clearing all data from list....
        arrayQuestion.clear();
        arrayAnswer.clear();
        arrayForm.clear();

        // Checking if database is empty....
        if(res.getCount() == 0) {
            Toast.makeText(this,"Add the values here",Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(res.moveToNext()){
                arrayQuestion.add(res.getString(0));
                arrayAnswer.add(res.getString(1));
                arrayForm.add(res.getString(2));
            }
        }


        // Setting values to Recycler View....
        adapter = new AdapterClassOther(arrayQuestion, arrayAnswer, arrayForm, this);
        allList.setAdapter(adapter);
    }

    // On back pressed...
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // OnRestart...
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
