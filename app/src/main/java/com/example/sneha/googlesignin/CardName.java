package com.example.sneha.googlesignin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class CardName extends AppCompatActivity {
    BankListAdapter adapter;
    String activity;

    MaterialSearchView searchView;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_card_name);
        activity = getIntent().getExtras().getString("NAME");
        ListView listView = (ListView) findViewById(R.id.list);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        final ArrayList<BankList> bank = new ArrayList<BankList>();
        bank.add(new BankList("VISA", R.drawable.visa));
        bank.add(new BankList("RuPay", R.drawable.rupay));
        bank.add(new BankList("Maestro", R.drawable.maestro));
        bank.add(new BankList("Mastercard", R.drawable.master));
        final ArrayList<BankList> allBank = new ArrayList<>(bank);
        adapter = new BankListAdapter(this, bank);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BankList banks=bank.get(position);
                if(activity.equals("editbankingcard")){
                    EditBanking.cardview.setText(banks.getName());
                    EditBanking.cardtype = banks.getName();
                    finish();
                }
                else if(activity.equals("bankingcard"))
                    {
                    Banking_card.cardview.setText(banks.getName());
                    Banking_card.cardtype = banks.getName();
                    Banking_card.cardview.setVisibility(View.VISIBLE);
                    finish();
                }

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                ArrayList<BankList> filteredList = new ArrayList<>();

                if(text == null || text.length() == 0){
                    filteredList.addAll(allBank);
                }else{
                    String filteredString = text.toLowerCase().trim();
                    for(BankList i : allBank){
                        if(i.getName().toLowerCase().contains(filteredString)){
                            filteredList.add(i);
                        }
                    }
                }

                bank.clear();
                bank.addAll(filteredList);

                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}


