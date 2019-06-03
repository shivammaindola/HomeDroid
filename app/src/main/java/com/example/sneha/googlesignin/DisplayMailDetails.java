package com.example.sneha.googlesignin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DisplayMailDetails extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    DatabaseH1 dataHelper;

    String emailaddress;

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
                Intent intent = new Intent(DisplayMailDetails.this,EditMail.class);
                intent.putExtra("mailaddress",emailaddress);
                startActivity(intent);
                return true;
            default: return false;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_mail_details);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.holdernmtextView);
        t2=(TextView)findViewById(R.id.passportnotextView);
        t3=(TextView)findViewById(R.id.placeeetextView);
        t4=(TextView)findViewById(R.id.dateofisstextView);
        //t5=(TextView)findViewById(R.id.user);
        t6=(TextView)findViewById(R.id.owner);




        dataHelper = new DatabaseH1(this);
        Bundle bundle = getIntent().getExtras();
        final  String namee = bundle.getString("mailaddress");

        Cursor res = dataHelper.GetTwoData(namee);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            emailaddress = res.getString(0);
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            //t5.setText(res.getString(4));
            t6.setText(res.getString(5));

        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    }

