package com.example.sneha.googlesignin;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class RemainderHome extends AppCompatActivity {
    CardView card1,card2,card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_remainder_home);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.changeEvents,new BirthdayFrag());
        fragmentTransaction.commit();
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card1.setCardBackgroundColor(Color.parseColor("#D3D3D3"));
                card2.setCardBackgroundColor(0);
                card3.setCardBackgroundColor(0);
              //  card2.setBackgroundResource(0);
               // card3.setBackgroundResource(0);
                Toast.makeText(getApplicationContext(),"ÿeahhhh",Toast.LENGTH_SHORT).show();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.changeEvents,new BirthdayFrag());
                fragmentTransaction.commit();
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card2.setCardBackgroundColor(Color.parseColor("#D3D3D3"));
              //  card1.setBackgroundResource(0);
                //card3.setBackgroundResource(0);
                card1.setCardBackgroundColor(0);
                card3.setCardBackgroundColor(0);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.changeEvents,new MarriageFrag());
                fragmentTransaction.commit();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                card3.setCardBackgroundColor( Color.parseColor("#D3D3D3"));
              //  card2.setBackgroundResource(0);
                //card1.setBackgroundResource(0);
                card2.setCardBackgroundColor(0);
                card1.setCardBackgroundColor(0);
                Toast.makeText(getApplicationContext(),"ÿeahhhh",Toast.LENGTH_SHORT).show();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.changeEvents,new EventFrag());
                fragmentTransaction.commit();
            }
        });
    }
}
