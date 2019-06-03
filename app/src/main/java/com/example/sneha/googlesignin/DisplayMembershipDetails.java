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

public class DisplayMembershipDetails extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    DatabaseHelper6 dataHelper;

    String membershipnumber;

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
                Intent intent = new Intent(DisplayMembershipDetails.this,EditMember.class);
                intent.putExtra("membershipnumber",membershipnumber);
                startActivity(intent);
                return true;
            default: return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_membership_details);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        t1=(TextView)findViewById(R.id.holdernmtextView);
        t2=(TextView)findViewById(R.id.passportnotextView);
        t3=(TextView)findViewById(R.id.placeeetextView);
        t4=findViewById(R.id.holdername);

        dataHelper = new DatabaseHelper6(this);
        Bundle bundle = getIntent().getExtras();
        final  String namee = bundle.getString("membershipnumber");

        Cursor res = dataHelper.GetTwoData(namee);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            t1.setText(res.getString(0));
            t2.setText(res.getString(1));
            membershipnumber = res.getString(1);
            t3.setText(res.getString(2));
            t4.setText(res.getString(3));


        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

