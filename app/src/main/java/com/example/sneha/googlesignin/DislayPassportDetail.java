package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DislayPassportDetail extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;
    DataHelper dataHelper;
    String pasnumber;

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
                Intent intent = new Intent(DislayPassportDetail.this,EditPassport.class);
                intent.putExtra("passportnum",pasnumber);
                startActivity(intent);
                return true;
            default: return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dislay_passport_detail);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.holdernmtextView);
        t2=(TextView)findViewById(R.id.passportnotextView);
        t3=(TextView)findViewById(R.id.placeeetextView);
        t4=(TextView)findViewById(R.id.dateofisstextView);
        t5=(TextView)findViewById(R.id.dateofexpptextView);
        dataHelper = new DataHelper(this);
        Bundle bundle = getIntent().getExtras();
        final  String namee = bundle.getString("passportum");

        Cursor res = dataHelper.GetTwoData(namee);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){



            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            pasnumber = res.getString(1);
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            t5.setText(res.getString(4));

        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
