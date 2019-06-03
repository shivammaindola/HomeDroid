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

public class DisplayInsurance extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
    DatabaseInsurance dataHelper;

    String name1, number1;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees1:
                Intent intent = new Intent(DisplayInsurance.this, EditInsurance.class);
                intent.putExtra("name", name1);
                intent.putExtra("number", number1);
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
        setContentView(R.layout.activity_display_insurance);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.plan);
        t3 = (TextView) findViewById(R.id.policyname);
        t4 = (TextView) findViewById(R.id.number);
        t5 = (TextView) findViewById(R.id.duration);
        t6 = (TextView) findViewById(R.id.premium);
        t7 = (TextView) findViewById(R.id.frequency);
        t8 = (TextView) findViewById(R.id.due);
        t9 = findViewById(R.id.holder);
        dataHelper = new DatabaseInsurance(this);
        Bundle bundle = getIntent().getExtras();
        name1 = bundle.getString("name");
        number1 = bundle.getString("number");

        Cursor res = dataHelper.GetTwoData(name1, number1);
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
            t8.setText(res.getString(7));
            if (res.getString(6).equals("--Select Month--"))
                t7.setText("");
            else
                t7.setText(res.getString(6));

            t9.setText(res.getString(9));
            name1 = res.getString(0);
            number1 = res.getString(3);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

