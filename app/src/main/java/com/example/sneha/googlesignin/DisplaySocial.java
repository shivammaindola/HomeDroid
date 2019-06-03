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

public class DisplaySocial extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7;
    DatabaseH dataHelper;


    String socialname, socialaddress;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees1:
                Intent intent = new Intent(DisplaySocial.this, EditSocial.class);
                intent.putExtra("sitename", socialname);
                intent.putExtra("mailaddress", socialaddress);
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
        setContentView(R.layout.activity_display_social);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        t1 = (TextView) findViewById(R.id.site);
        t2 = (TextView) findViewById(R.id.username);
        t3 = (TextView) findViewById(R.id.password);
        t4 = (TextView) findViewById(R.id.question);
        t5 = (TextView) findViewById(R.id.answer);
        t6 = (TextView) findViewById(R.id.recovery);
        t7 = (TextView) findViewById(R.id.holder);


        dataHelper = new DatabaseH(this);
        Bundle bundle = getIntent().getExtras();
        final String namee = bundle.getString("sitename");
        final String mail = bundle.getString("mailaddress");
        Cursor res = dataHelper.GetTwoData(namee, mail);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            t5.setText(res.getString(4));
            t6.setText(res.getString(5));
            t7.setText(res.getString(7));
            socialname = res.getString(0);
            socialaddress = res.getString(1);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

