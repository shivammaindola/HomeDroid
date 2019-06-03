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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class DrivingLicense1 extends AppCompatActivity {

    // Initializations...
    MaterialSearchView searchView;
    ImageView imgView;
    RecyclerView allList;
    ArrayList<String> nameList;
    ArrayList<String> numberList;
    DatabaseHelper12 database;
    AdapterClassDrivingLicense adapter;

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
                Intent intent = new Intent(DrivingLicense1.this, DrivingLicense.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    // OnCreate....
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_driving_license1);

        // Initialization...
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        allList = findViewById(R.id.AllList);
        database = new DatabaseHelper12(DrivingLicense1.this);
        nameList = new ArrayList<>();
        numberList = new ArrayList<>();

        // Setting the recycler View....
        allList.setHasFixedSize(true);
        allList.setLayoutManager(new LinearLayoutManager(this));
        setAdapter();

        // Setting toolbar..
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this, R.style.Cambria);



    }

    private void setAdapter() {
        Cursor res = database.getAllData();

        // Clearing all data from list....
        nameList.clear();
        numberList.clear();

        // Checking if database is empty....
        if(res.getCount() == 0) {
            Toast.makeText(this,"Add the values here",Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(res.moveToNext()){
                nameList.add(res.getString(0));
                numberList.add(res.getString(1));
            }
        }

        // Setting values to Recycler View....
        adapter = new AdapterClassDrivingLicense(nameList, numberList, this);
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
