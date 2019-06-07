package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DisplayBanking extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    DatabaseHelper4 dataHelper;
    SimpleDateFormat sdf;
    SimpleDateFormat input;

    String cardnumber;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees1:
                Intent intent = new Intent(DisplayBanking.this, EditBanking.class);
                intent.putExtra("cardnumber", cardnumber);
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
        setContentView(R.layout.activity_display_banking);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        t1 = (TextView) findViewById(R.id.bank);
        t2 = (TextView) findViewById(R.id.card);
        t3 = (TextView) findViewById(R.id.cvv1);
        t4 = (TextView) findViewById(R.id.date);
        t5 = (TextView) findViewById(R.id.mobile);
        t6 = (TextView) findViewById(R.id.credit);
        t7 = (TextView) findViewById(R.id.cardholder);
        t8 = (TextView) findViewById(R.id.expiry);
        t11 = (TextView) findViewById(R.id.type);
        t12 = (TextView) findViewById(R.id.validfrom);
        t9 = findViewById(R.id.pin);


        sdf = new SimpleDateFormat("MMM yyyy");
        input = new SimpleDateFormat("yyyy-MM-dd");
        dataHelper = new DatabaseHelper4(this);
        Bundle bundle = getIntent().getExtras();
        final String namee = bundle.getString("cardnumber");

        Cursor res = dataHelper.GetTwoData(namee);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            t11.setText(res.getString(8) + " " + res.getString(9));
            t3.setText(res.getString(2));
            t4.setText(formatMonthYear(res.getString(3)));

            t5.setText(res.getString(4));
            t6.setText(res.getString(5));
            t7.setText(res.getString(6));
            t8.setText(res.getString(7));
            Log.e("Tag", "2");
            if(res.getString(13).equals("")){
                t12.setText("");
            }else {
                t12.setText(formatMonthYear(res.getString(13)));
            }

            t9.setText(res.getString(14));

            cardnumber = res.getString(1);

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

}

