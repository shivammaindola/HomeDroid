package com.example.sneha.googlesignin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class OperatingSystemName extends AppCompatActivity {
    OperatingSystemAdapter adapter;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.operating_system_names);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

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


    }
}
