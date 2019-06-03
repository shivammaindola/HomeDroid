package com.example.sneha.googlesignin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class CardName extends AppCompatActivity {
    BankListAdapter adapter;
String activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_card_name);
        activity = getIntent().getExtras().getString("NAME");
        ListView listView = (ListView) findViewById(R.id.list);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        final ArrayList<BankList> bank = new ArrayList<BankList>();
        bank.add(new BankList("VISA", R.drawable.visa));
        bank.add(new BankList("RuPay", R.drawable.rupay));
        bank.add(new BankList("Maestro", R.drawable.maestro));
        bank.add(new BankList("Mastercard", R.drawable.master));
        adapter = new BankListAdapter(this, bank);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BankList banks=bank.get(position);
                if(activity.equals("editbankingcard")){
                    EditBanking.cardview.setText(banks.getName());
                    EditBanking.cardtype=banks.getName();
                    finish();
                }
                else if(activity.equals("bankingcard"))
                    {
                    Banking_card.cardview.setText(banks.getName());
                    Banking_card.cardtype=banks.getName();
                    Banking_card.cardview.setVisibility(View.VISIBLE);
                    finish();
                }

            }
        });
    }
}


