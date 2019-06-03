package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DebitCredit extends AppCompatActivity {
    BankListAdapter adapter;
    String activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_debit_credit);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        activity = getIntent().getExtras().getString("NAME");
        ListView listView = (ListView) findViewById(R.id.list);

        final ArrayList<BankList> bank = new ArrayList<BankList>();
        bank.add(new BankList("Credit", R.drawable.debitcrdedit));
        bank.add(new BankList("Debit", R.drawable.debitcrdedit));

        adapter = new BankListAdapter(this, bank);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BankList banks=bank.get(position);

            }
        });
    }
}


