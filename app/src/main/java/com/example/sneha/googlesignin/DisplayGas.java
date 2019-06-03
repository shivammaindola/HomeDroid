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

public class DisplayGas extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    DatabaseGas dataHelper;

    String connectionno;

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
                Intent intent = new Intent(DisplayGas.this,EditGas.class);
                intent.putExtra("connectionno",connectionno);
                startActivity(intent);
                return true;
            default: return false;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_gas); Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.number);
        t2=(TextView)findViewById(R.id.name);
        t3=(TextView)findViewById(R.id.address);
        t4=findViewById(R.id.username);
        t5=(TextView)findViewById(R.id.password);
        t6=(TextView)findViewById(R.id.phone);
        t7=findViewById(R.id.email);
        dataHelper = new DatabaseGas(this);
        Bundle bundle = getIntent().getExtras();
        final  String namee = bundle.getString("connectionno");

        Cursor res = dataHelper.GetTwoData(namee);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            connectionno= res.getString(0);
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            t5.setText(res.getString(4));
            t6.setText(res.getString(5));
            t7.setText(res.getString(6));


        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

