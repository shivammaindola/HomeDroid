package com.example.sneha.googlesignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;

import me.anwarshahriar.calligrapher.Calligrapher;

public class allsensitive extends AppCompatActivity  {
    CardView pass ,voter,pan,aadhar,banking,bankdetail,membership,wifipass,other,mail,social,online,insurance,remote,gas,elec,sys, driving;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_allsensitive);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        pass=(CardView)findViewById(R.id.card1);
        voter=(CardView)findViewById(R.id.card2);
        pan=(CardView)findViewById(R.id.card3);
        aadhar=(CardView)findViewById(R.id.card4);
        banking=(CardView)findViewById(R.id.card5);
        bankdetail=(CardView)findViewById(R.id.card6);
        wifipass=(CardView)findViewById(R.id.card7);
        membership=(CardView)findViewById(R.id.card8);
        mail=(CardView)findViewById(R.id.card9);
        social=(CardView)findViewById(R.id.card10);
        online=(CardView)findViewById(R.id.card11);
        insurance=(CardView)findViewById(R.id.card12);
        other=(CardView)findViewById(R.id.card13);
        elec=(CardView)findViewById(R.id.card14);
        gas=(CardView)findViewById(R.id.card15);
        sys=(CardView)findViewById(R.id.card16);
        remote=(CardView)findViewById(R.id.card17);
        driving = (CardView)findViewById(R.id.card18);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(allsensitive.this,Passport.class);

                startActivity(i);
            }
        });
        voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,voterid1.class));

            }
        });
        pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,pancard1.class));

            }
        });
        aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,aadhar1.class));

            }
        });
        banking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,BankingCard1.class));

            }
        });
        bankdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Account1.class));

            }
        });
        wifipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Wifipass1.class));

            }
        });
        membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,membership1.class));

            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Mail1.class));

            }
        });
        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,SocialSites1.class));

            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,OnlineSites1.class));

            }
        });
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Insurance1.class));

            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Electricity1.class));

            }
        });
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Gas1.class));

            }
        });
        sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this, SystemLogins1.class));

            }
        });
        remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this, RemoteLogin1.class));

            }
        });
        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allsensitive.this, DrivingLicense1.class));
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allsensitive.this,Other.class));

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}



