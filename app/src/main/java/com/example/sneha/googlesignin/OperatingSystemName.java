package com.example.sneha.googlesignin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class OperatingSystemName extends AppCompatActivity {
    OperatingSystemAdapter adapter;
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
        setContentView(R.layout.operating_system_names);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        ListView listView = (ListView) findViewById(R.id.list);
        activity = getIntent().getExtras().getString("NAME");
        final ArrayList<OperatingSystemList>os = new ArrayList<OperatingSystemList>();

        os.add(new OperatingSystemList("Android", R.drawable.android));
        os.add(new OperatingSystemList("CentOS", R.drawable.centos));
        os.add(new OperatingSystemList("Debian", R.drawable.debian));
        os.add(new OperatingSystemList("Fedora", R.drawable.fedora));
        os.add(new OperatingSystemList("Generic Linux", R.drawable.genreic_linux));
        os.add(new OperatingSystemList("iOS", R.drawable.ios));
        os.add(new OperatingSystemList("Kali Linux", R.drawable.kali_linux));
        os.add(new OperatingSystemList("MacOS", R.drawable.mac_os));
        os.add(new OperatingSystemList("Open SUSE", R.drawable.open_suse));
        os.add(new OperatingSystemList("Red Hat", R.drawable.red_hat));
        os.add(new OperatingSystemList("Ubuntu", R.drawable.ubuntu));
        os.add(new OperatingSystemList("Windows 7", R.drawable.windows_7));
        os.add(new OperatingSystemList("Windows 8", R.drawable.windows_8));
        os.add(new OperatingSystemList("Windows 10", R.drawable.windows_10));
        os.add(new OperatingSystemList("Windows 2000", R.drawable.windows_2000));
        os.add(new OperatingSystemList("Windows Vista", R.drawable.windows_vista));
        os.add(new OperatingSystemList("Windows XP", R.drawable.windows_xp));
        os.add(new OperatingSystemList("Windows Server", R.drawable.windows_8));

        final ArrayList<OperatingSystemList> allOs = new ArrayList<>(os);
        adapter = new OperatingSystemAdapter(this, os);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                OperatingSystemList os_num = os.get(position);

                if(activity.equals("SystemLogin")) {
                    SystemLogin.osName = os_num.getName();
                    SystemLogin.textOS.setText(os_num.getName());
                    SystemLogin.textOS.setVisibility(View.VISIBLE);
                    finish();
                }
                else if(activity.equals("EditSystemLogin")){
                    EditSystemLogins.osName = os_num.getName();
                    EditSystemLogins.textOS.setText(os_num.getName());
                    EditSystemLogins.textOS.setVisibility(View.VISIBLE);
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
                ArrayList<OperatingSystemList> filteredList = new ArrayList<>();

                if(text == null || text.length() == 0){
                    filteredList.addAll(allOs);
                }else{
                    String filteredString = text.toLowerCase().trim();
                    for(OperatingSystemList i : allOs){
                        if(i.getName().toLowerCase().contains(filteredString)){
                            filteredList.add(i);
                        }
                    }
                }

                os.clear();
                os.addAll(filteredList);

                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}
