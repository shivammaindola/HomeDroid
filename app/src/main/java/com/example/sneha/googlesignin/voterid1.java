package com.example.sneha.googlesignin;

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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class voterid1 extends AppCompatActivity {
    ImageView imgView;
    RecyclerView allList;

    MaterialSearchView searchView;

    static ArrayList<String> voternamelist;
    static ArrayList<String> voternumlist;
    static ArrayList<String> a;
    static ArrayList<String> b;
    String title;

    DatabaseHelper1 db;

    static AdapterClassVoter adapterClass;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass1, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees11 :
                Intent intent = new Intent(voterid1.this,voterid.class);
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_voterid1);

        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        //imgView = findViewById(R.id.imghere);
        allList = findViewById(R.id.AllList2);

        db = new DatabaseHelper1(this);

        voternamelist = new ArrayList<>();
        voternumlist = new ArrayList<>();
        a = new ArrayList<>();
        b = new ArrayList<>();

        allList.setHasFixedSize(true);
        allList.setLayoutManager(new LinearLayoutManager(this));
        //allList.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
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
                if(newText!=NULL && !newText.isEmpty())
                {
                    db.QUERYs(newText);

                    adapterClass = new AdapterClassVoter(a, b, voterid1.this);
                    allList.setAdapter(adapterClass);
                    adapterClass.notifyDataSetChanged();

                }
                else
                    setAdapter();
                return true;
            }
        });
        title=this.getClass().getSimpleName();
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }

    private void startNewActivity() {
        Intent intent = new Intent(voterid1.this,voterid.class);
        startActivity(intent);
    }

    private void setAdapter() {

        Cursor res = db.getAllData();
        voternamelist.clear();
        voternumlist.clear();
        if(res.getCount() == 0) {
            Toast.makeText(voterid1.this,"Add the values here",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (res.moveToNext()) {
                voternumlist.add(res.getString(0));
                voternamelist.add(res.getString(1));
            }

            adapterClass = new AdapterClassVoter(voternumlist, voternamelist, voterid1.this);
            allList.setAdapter(adapterClass);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // startActivity(new Intent(voterid1.this,allsensitive.class));
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
