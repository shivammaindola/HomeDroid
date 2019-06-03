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

public class DisplayVoterDetails extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    DatabaseHelper1 dataHelper;

    String voternumber;

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
                Intent intent = new Intent(DisplayVoterDetails.this,EditVoter.class);
                intent.putExtra("voterid_number",voternumber);
                startActivity(intent);
                return true;
            default: return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_voter_details);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.holdernmtextView1);
        t2=(TextView)findViewById(R.id.passportnotextView1);
        t3=(TextView)findViewById(R.id.placeeetextView1);
        t4=(TextView)findViewById(R.id.dateofisstextView1);
        t5=(TextView)findViewById(R.id.dateofexpptextView1);
        t6=(TextView)findViewById(R.id.t6);



        dataHelper = new DatabaseHelper1(this);
        Bundle bundle = getIntent().getExtras();
        final  String number1= bundle.getString("voterid_number");

        Cursor res = dataHelper.GetTwoData(number1);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            voternumber = res.getString(0);
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));
            t5.setText(res.getString(4));
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

