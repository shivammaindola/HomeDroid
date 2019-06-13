package com.example.sneha.googlesignin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class aadhar1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView imgView;
    RecyclerView allList;
    MaterialSearchView searchView;

    static ArrayList<String> a;
    static ArrayList<String> b;
    static ArrayList<String> aadharnamelist;
    static ArrayList<String> aadharnumberlist;

    DatabaseHelper3 db;

    static AdapterClassAadhar adapterClass;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass1, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees11:
                Intent intent = new Intent(aadhar1.this, aadhar.class);
                //intent.putExtra("passportnum",pasnumber);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar1);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        //imgView = findViewById(R.id.imghere);
        allList = findViewById(R.id.AllList);

        db = new DatabaseHelper3(this);

        aadharnamelist = new ArrayList<>();
        aadharnumberlist = new ArrayList<>();
        a=new ArrayList<>();
        b=new ArrayList<>();
        allList.setHasFixedSize(true);
        allList.setLayoutManager(new LinearLayoutManager(this));
       // allList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        setAdapter();
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                //setAdapter();
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=NULL&&!newText.isEmpty())
                {
                    db.QUERYs(newText);

                    adapterClass = new AdapterClassAadhar(a, b, aadhar1.this);
                    allList.setAdapter(adapterClass);
                    adapterClass.notifyDataSetChanged();

                }
                else
                    setAdapter();
                return true;
            }
        });

    }
    private void startNewActivity() {
        Intent intent = new Intent(aadhar1.this, aadhar.class);
        startActivity(intent);
    }

    private void setAdapter() {

        Cursor res = db.getAllData();
        aadharnamelist.clear();
        aadharnumberlist.clear();
        if (res.getCount() == 0) {
            // show message
            Toast.makeText(aadhar1.this, "Add the values here", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (res.moveToNext()) {
                aadharnamelist.add(res.getString(0));
                aadharnumberlist.add(res.getString(1));
            }

            adapterClass = new AdapterClassAadhar(aadharnamelist, aadharnumberlist, aadhar1.this);
            allList.setAdapter(adapterClass);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // startActivity(new Intent(aadhar1.this,allsensitive.class));
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
