package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class ComponyProfile1 extends AppCompatActivity {


    // Initializations...
    MaterialSearchView searchView;
    ArrayList<String> ventureNameList;
    AdapterClassCompany adapter;
    DatabaseHelper13 database;
    RecyclerView allList;

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
                Intent intent = new Intent(ComponyProfile1.this, ComponyProfile.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compony_profile1);

        // Initialization...
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ventureNameList = new ArrayList<>();
        database = new DatabaseHelper13(this);
        allList = (RecyclerView) findViewById(R.id.AllList);

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
        ventureNameList.clear();

        if(res.getCount() == 0) {
            Toast.makeText(this,"Add the values here",Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(res.moveToNext()){
                ventureNameList.add(res.getString(0));
            }
        }

        // Setting values to Recycler View....
        adapter = new AdapterClassCompany(ventureNameList, this);
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
