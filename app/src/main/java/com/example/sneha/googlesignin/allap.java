package com.example.sneha.googlesignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import me.anwarshahriar.calligrapher.Calligrapher;

public class allap extends AppCompatActivity {

   ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_allap);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        addListnerOnButton();
    }
    public void addListnerOnButton(){
        imageButton = (ImageButton)findViewById(R.id.other);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==imageButton)
                {
                    //startActivity(new Intent(allap.this,logincredentials.class));
                }
            }
        });
    }
}
