package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DisplayRemoteDetail extends AppCompatActivity {
    TextView t1, t2, t3;
    DatabaseRemote dataHelper;

    String APPNAME;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees1:
                Intent intent = new Intent(DisplayRemoteDetail.this, EditRemoteLogin.class);
                intent.putExtra("appname", APPNAME);
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
        setContentView(R.layout.activity_display_remote_detail);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.username);
        t3 = (TextView) findViewById(R.id.password);
        dataHelper = new DatabaseRemote(this);
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("appname");
        Cursor res = dataHelper.GetTwoData(name);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            t3.setText(res.getString(2));
            APPNAME = res.getString(0);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

