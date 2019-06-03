package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Other extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton floatingActionButton ;
    DatabaseHelper11 mydb;
    EditText question,answer;
    Button update,view,submit;
    LinearLayout lb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_other);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        mydb= new DatabaseHelper11(this);
        question=(EditText)findViewById(R.id.question);
        answer=(EditText)findViewById(R.id.answer);
        submit=(Button)findViewById(R.id.submit);
        lb=(LinearLayout)findViewById(R.id.lb);
        update=(Button)findViewById(R.id.update);

        view=(Button)findViewById(R.id.view);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=(LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final  View addView=layoutInflater.inflate(R.layout.row,null);
                lb.addView(addView);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb.insertData(question.getText().toString(),answer.getText().toString());
                if(isInserted==true){
                    Toast.makeText(Other.this,"Data inserted",Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(Other.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=mydb.update(question.getText().toString(),answer.getText().toString()
                );
                if(isUpdated==true){
                    Toast.makeText(Other.this,"Data is updated",Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(Other.this,"Data is not updated",Toast.LENGTH_LONG).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(""+res.getString(0)+"\n");
                    buffer.append(""+res.getString(1)+"\n\n");



                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
