
package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DisplayAccount extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    DatabaseHelper5 dataHelper;

    String accountnumber;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees1 :
                Intent intent = new Intent(DisplayAccount.this,EditAccount.class);
                intent.putExtra("accountnumber",accountnumber);
                startActivity(intent);
                return true;
            default: return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_account);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.bank);
        t2=(TextView)findViewById(R.id.number);
        t3=(TextView)findViewById(R.id.holder);
        t4=(TextView)findViewById(R.id.ifsc);
        t5=(TextView)findViewById(R.id.nominee);
        t6=(TextView)findViewById(R.id.relation);
        t7=(TextView)findViewById(R.id.password);



        dataHelper = new DatabaseHelper5(this);
        Bundle bundle = getIntent().getExtras();
        final  String namee = bundle.getString("accountnumber");

        Cursor res = dataHelper.GetTwoData(namee);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            t5.setText(res.getString(4));
            t6.setText(res.getString(5));
            t7.setText(res.getString(6));

            accountnumber = res.getString(1);
        }
    }@Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    }

