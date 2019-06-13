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

public class BankName extends AppCompatActivity {
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
        setContentView(R.layout.activity_bank_name);
        final ListView listView = (ListView) findViewById(R.id.list);
        activity = getIntent().getExtras().getString("NAME");

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        final ArrayList<BankList> bank = new ArrayList<BankList>();
        bank.add(new BankList("Abhyudaya Cooperative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Adarsh Co-operative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Aditya Birla Idea Payments Bank", R.drawable.bank));
        bank.add(new BankList("Airtel Payments Bank", R.drawable.airtel));
        bank.add(new BankList("Allahabad Bank", R.drawable.allahabad));
        bank.add(new BankList("Allahabad UP Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Andhra Bank", R.drawable.andhra));
        bank.add(new BankList("Andhra Pradesh Grameena Vikas Bank", R.drawable.bank));
        bank.add(new BankList("Andhra pragathi Grameena Bank", R.drawable.bank));
        bank.add(new BankList("A P Mahesh Bank", R.drawable.bank));
        bank.add(new BankList("Apna Sahakari Bank", R.drawable.apnasahakari));
        bank.add(new BankList("Assam Gramin Vikash Bank", R.drawable.bank));
        bank.add(new BankList("Axis Bank", R.drawable.axis));
        bank.add(new BankList("Bandhan Bank", R.drawable.bank));
        bank.add(new BankList("Bank of Baroda", R.drawable.bankofbaroda));
        bank.add(new BankList("Bank of India", R.drawable.boi));
        bank.add(new BankList("Bank of Maharashtra", R.drawable.bank));
        bank.add(new BankList("Baroda Gujarat Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Baroda Rajasthan Kshetriya Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Baroda UP Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Bassein Catholic Co-operative Bank", R.drawable.bank));
        bank.add(new BankList("BHILWARA URBAN CO OPERATIVE BANK LTD", R.drawable.bank));
        bank.add(new BankList("Bihar Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Canara Bank", R.drawable.canara));
        bank.add(new BankList("Catholic Syrian Bank", R.drawable.catholicsyrian));
        bank.add(new BankList("Central  Bank of India", R.drawable.centralbankofindia));
        bank.add(new BankList("Chaitanya Godavari Grameena Bank", R.drawable.bank));
        bank.add(new BankList("Chattisgarh R G Bank", R.drawable.bank));
        bank.add(new BankList("Citibank", R.drawable.citi));
        bank.add(new BankList("City Union Bank", R.drawable.bank));
        bank.add(new BankList("Coastal Local Area  Ltd", R.drawable.bank));
        bank.add(new BankList("Corporation Bank", R.drawable.bank));
        bank.add(new BankList("Cosmos Bank", R.drawable.bank));
        bank.add(new BankList("DBS Bank", R.drawable.dbs));
        bank.add(new BankList("DCB", R.drawable.bank));
        bank.add(new BankList("Dena Bank", R.drawable.dena));
        bank.add(new BankList("Dena Gujarat Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Deutsche Bank", R.drawable.bank));
        bank.add(new BankList("Dhanlaxmi Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Dombivali Nagri Sahakari Bank", R.drawable.bank));
        bank.add(new BankList("Equitas Bank", R.drawable.equitas));
        bank.add(new BankList("ESAF SMALL FINANCE BANK LTD", R.drawable.bank));
        bank.add(new BankList("Federal Bank", R.drawable.federal));
        bank.add(new BankList("Fino Payments Bank", R.drawable.bank));
        bank.add(new BankList("GP Parsik Bank", R.drawable.gpparsik));
        bank.add(new BankList("HDFC Bank", R.drawable.hdfc));
        bank.add(new BankList("Himachal Pradesh Gramin Bank", R.drawable.bank));
        bank.add(new BankList("HSBC", R.drawable.hsbc));
        bank.add(new BankList("Hutatma Sahakari Bank Ltd", R.drawable.bank));
        bank.add(new BankList("ICICI Bank", R.drawable.icici));
        bank.add(new BankList("IDBI Bank", R.drawable.idbi));
        bank.add(new BankList("IDFC Bank", R.drawable.bank));
        bank.add(new BankList("Indian Bank", R.drawable.indian));
        bank.add(new BankList("Indian overseas Bank", R.drawable.overseas));
        bank.add(new BankList("India Post Payment Bank", R.drawable.bank));
        bank.add(new BankList("Indusland Bank", R.drawable.indusland));
        bank.add(new BankList("J&K Grameen Bank", R.drawable.bank));
        bank.add(new BankList("Jalgaon janata Sahakari Bank Ltd Jalgaon", R.drawable.bank));
        bank.add(new BankList("Jammu & Kashmir Bank", R.drawable.bank));
        bank.add(new BankList("Jana Small Finance", R.drawable.bank));
        bank.add(new BankList("Janta Sahakari Bank", R.drawable.bank));
        bank.add(new BankList("Jio Payments Bank", R.drawable.bank));
        bank.add(new BankList("KAIJSB - Ichalkaranji", R.drawable.bank));
        bank.add(new BankList("Kalyan Janta Sahakari Bank", R.drawable.bank));
        bank.add(new BankList("Karnataka Bank", R.drawable.karnataka));
        bank.add(new BankList("Karnataka Vikas Grameena Bank", R.drawable.bank));
        bank.add(new BankList("Karur Vysya Bank", R.drawable.bank));
        bank.add(new BankList("Kashi Gomti Samyut Grameen Bank", R.drawable.bank));
        bank.add(new BankList("KAVERI GRAMEENA BANK", R.drawable.bank));
        bank.add(new BankList("Kerala Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Kotak Mahindra Bank", R.drawable.kotak));
        bank.add(new BankList("Lakshmi Vilas Bank", R.drawable.lakshmi));
        bank.add(new BankList("Langpi dehangi Rural Bank", R.drawable.bank));
        bank.add(new BankList("Madhya bihar Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Maharashtra Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Maharashtra State Co-op Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Malwa Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Manipur rural bank", R.drawable.bank));
        bank.add(new BankList("Maratha Cooperative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Meghalaya Rural Bank", R.drawable.bank));
        bank.add(new BankList("Mehsana Urban Bank", R.drawable.mehsana));
        bank.add(new BankList("Mehsana Urban Bank", R.drawable.bank));
        bank.add(new BankList("Mizoram Rural Bank", R.drawable.bank));
        bank.add(new BankList("Nainital Bank", R.drawable.bank));
        bank.add(new BankList("NIKGSB Bank", R.drawable.bank));
        bank.add(new BankList("Oriental bank of Commerce", R.drawable.obc));
        bank.add(new BankList("PASCHIM BANGA GRAMIN BANK", R.drawable.bank));
        bank.add(new BankList("Paytm Payments Bank", R.drawable.paytm));
        bank.add(new BankList("PMC Bank", R.drawable.pmc));
        bank.add(new BankList("Pragathi Krishna Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Prathama Bank", R.drawable.bank));
        bank.add(new BankList("Punjab and Sind Bank", R.drawable.bank));
        bank.add(new BankList("Punjab Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Punjab National bank", R.drawable.pnb));
        bank.add(new BankList("Purvanchal Bank", R.drawable.bank));
        bank.add(new BankList("Rajasthan Marudhara Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Rajkot Nagrik Sahakari Bank", R.drawable.rajkot));
        bank.add(new BankList("RBL", R.drawable.rbl));
        bank.add(new BankList("Samruddhi Co-operative Bank Ltd., Nagpur", R.drawable.bank));
        bank.add(new BankList("Saraswat Bank", R.drawable.saraswat));
        bank.add(new BankList("Sarva Haryana Bank", R.drawable.bank));
        bank.add(new BankList("SARVA UP GTRAMIN BANK", R.drawable.bank));
        bank.add(new BankList("Saurashtra Gramin Bank", R.drawable.bank));
        bank.add(new BankList("SHREE KADI NAGRIK SAHAKARI BANK LTD", R.drawable.bank));
        bank.add(new BankList("South Indian Bank", R.drawable.southindian));
        bank.add(new BankList("Standard Chartered", R.drawable.chartered));
        bank.add(new BankList("State Bank of India", R.drawable.sbi));
        bank.add(new BankList("SUCO SOUHARDA SAHAKARI BANK LTD", R.drawable.bank));
        bank.add(new BankList("Surat People Cooperative Bank", R.drawable.bank));
        bank.add(new BankList("Suryoday Small Finance Bank Ltd", R.drawable.bank));
        bank.add(new BankList("SVC Co-operative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("Syndicate Bank", R.drawable.syndicate));
        bank.add(new BankList("Tamilnad Mercantile Bank", R.drawable.bank));
        bank.add(new BankList("Telangana Grameena Bank", R.drawable.bank));
        bank.add(new BankList("Telangana State Co Op Apex Bank", R.drawable.bank));
        bank.add(new BankList("Thane Bharat Sahakari Bank", R.drawable.bank));
        bank.add(new BankList("The Ahmedabad District Cooperative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Ahmedabad Mercantile Cooperative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Andhra Pradesh State Co-operative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Gujarat State Cooperative Bank", R.drawable.bank));
        bank.add(new BankList("The Hasti Co-operative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Mahanagar Co.Op. Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Malad Sahakari bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Municipal Co-op Bank ltd", R.drawable.bank));
        bank.add(new BankList("The Muslim Cooperative Bank Ltd", R.drawable.bank));
        bank.add(new BankList("The Sutex Co.op. Bank Ltd", R.drawable.bank));
        bank.add(new BankList("THE UDAIPUR MAHILA SAMRIDHI URBAN COOPERATIVE ", R.drawable.bank));
        bank.add(new BankList("THE UDAIPUR MAHILA URBAN CO OPERATIVE", R.drawable.bank));
        bank.add(new BankList("THE URBAN CO OP BANK LTD DHARANGAON", R.drawable.bank));
        bank.add(new BankList("The varachha Co-Op BAnk Ltd", R.drawable.bank));
        bank.add(new BankList("THE VIJAY COOPERATIVE BANK LTD", R.drawable.bank));
        bank.add(new BankList("The Vishweshwar Sahakari Bank Ltd, Pune", R.drawable.bank));
        bank.add(new BankList("TJSB Bank", R.drawable.tjsb));
        bank.add(new BankList("Tripura Gramin Bank", R.drawable.bank));
        bank.add(new BankList("UCO Bank", R.drawable.uco));
        bank.add(new BankList("Ujjivan Small Finance Bank", R.drawable.bank));
        bank.add(new BankList("Union Bank of India", R.drawable.union));
        bank.add(new BankList("United Bank of India", R.drawable.united));
        bank.add(new BankList("Uttrakhand Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Vanachal Gramin Bank", R.drawable.bank));
        bank.add(new BankList("Vasai vikas Sahakari Bank", R.drawable.vasaivikas));
        bank.add(new BankList("Vijaya Bank", R.drawable.vijayabank));
        bank.add(new BankList("Yes Bank", R.drawable.yesbank));

        final ArrayList<BankList> allBank = new ArrayList<>(bank);

        adapter = new BankListAdapter(this, bank);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BankList banks = bank.get(position);
                if (activity.equals("bankingcard")) {
                    Banking_card.bankname = banks.getName();
                    Banking_card.bankview.setText(banks.getName());
                    Banking_card.bankview.setVisibility(View.VISIBLE);
                    finish();
                }
                else if(activity.equals("editbankingcard")){
                    EditBanking.bankname = banks.getName();
                    EditBanking.bankview.setText(banks.getName());
                    finish();
                }
                else if(activity.equals("editbank")){
                    EditAccount.bankname = banks.getName();
                    EditAccount.bankview.setText(banks.getName());
                    finish();
                }
                else {
                    Account_details.bankname = banks.getName();
                    Account_details.bankview.setText(banks.getName());
                    Account_details.bankview.setVisibility(View.VISIBLE);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}