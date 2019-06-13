package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class BankingCard1 extends AppCompatActivity {
    RecyclerView allList;
    MaterialSearchView searchView;
    static ArrayList<String> banknamelist;
    static ArrayList<String> cardNumberlist;
    static ArrayList<String> cardlist;
    static ArrayList<String> holderNameList;
    static ArrayList<String> cardTypeList;
    static ArrayList<Integer> bankViewList;
    static ArrayList<Integer> cardTypeLogoList;
    static ArrayList<String> a;
    static ArrayList<String> b;
    static ArrayList<String> c;
    static ArrayList<String> d;
    static ArrayList<String> e;
    static ArrayList<Integer> f;
    static ArrayList<Integer> g;
    DatabaseHelper4 db;
    LogoName logoName;

    static AdapterClassBanking adapterClass;

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
                Intent intent = new Intent(BankingCard1.this, Banking_card.class);
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
        setContentView(R.layout.activity_banking_card1);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        allList = findViewById(R.id.AllList);

        db = new DatabaseHelper4(this);
        a = new ArrayList<>();
        b = new ArrayList<>();
        c = new ArrayList<>();
        d = new ArrayList<>();
        e = new ArrayList<>();
        f = new ArrayList<>();
        g = new ArrayList<>();
        banknamelist = new ArrayList<>();
        cardlist = new ArrayList<>();
        cardNumberlist = new ArrayList<>();
        holderNameList = new ArrayList<>();
        cardTypeList = new ArrayList<>();
        bankViewList = new ArrayList<>();
        cardTypeLogoList = new ArrayList<>();
        logoName = new LogoName();


        allList.setHasFixedSize(true);
        allList.setLayoutManager(new LinearLayoutManager(this));
        setAdapter();
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
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

                    adapterClass = new AdapterClassBanking(a, b, c, d, e, f, g,BankingCard1.this);
                    allList.setAdapter(adapterClass);
                    adapterClass.notifyDataSetChanged();
                }
                else
                    setAdapter();
                return true;
            }
        });
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    }
    private void startNewActivity() {
        Intent intent = new Intent(BankingCard1.this, Banking_card.class);
        startActivity(intent);
    }

    private void setAdapter() {

        Cursor res = db.getAllData();
        banknamelist.clear();
        cardlist.clear();
        cardNumberlist.clear();
        cardTypeList.clear();
        holderNameList.clear();
        bankViewList.clear();
        cardTypeLogoList.clear();

        if (res.getCount() == 0) {
            // show message
            Toast.makeText(BankingCard1.this, "Add the values here", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (res.moveToNext()) {
                String name = res.getString(0);
                String card = res.getString(9);

                banknamelist.add(res.getString(0));
                cardNumberlist.add(res.getString(1));
                holderNameList.add(res.getString(2));
                cardlist.add(res.getString(8));
                cardTypeList.add(res.getString(9));

                bankViewList.add(logoName.getLogo(name));
                cardTypeLogoList.add(logoName.getLogo(card));
            }

            adapterClass = new AdapterClassBanking(
                    banknamelist,
                    cardlist,
                    holderNameList,
                    cardNumberlist,
                    cardTypeList,
                    bankViewList,
                    cardTypeLogoList,
                    BankingCard1.this);

            allList.setAdapter(adapterClass);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
