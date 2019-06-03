package com.example.sneha.googlesignin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class StdCode extends AppCompatActivity {
    StdCodesNames db;
   // Button std;
    ImageView ok;
    LinearLayout linearLayout;
    TextInputLayout stdname,stdcode;
    String num1,num2;
    ArrayAdapter arrayAdapter;
    ListView listView;

    ArrayList<String> allstdcodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_std_code);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        db= new StdCodesNames(this);

        stdname = (TextInputLayout)findViewById(R.id.placename);
        stdcode = (TextInputLayout)findViewById(R.id.placecode);
        linearLayout = (LinearLayout)findViewById(R.id.linear);
        ok=(ImageView) findViewById(R.id.ok);
        boolean checkStatus = db.InsertTheValues();
        if(checkStatus)
        {
            Toast.makeText(getApplicationContext(),"Hurray, Success",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
        }

       // std=(Button)findViewById(R.id.std);
       // linearLayout.setVisibility(View.INVISIBLE);
        listView = (ListView)findViewById(R.id.listview);
        setArrayList();

        arrayAdapter = new ArrayAdapter(StdCode.this,android.R.layout.simple_list_item_1, allstdcodes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                new android.app.AlertDialog.Builder(StdCode.this)
                        .setIcon(R.drawable.common_google_signin_btn_icon_dark_focused)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure that you want to delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                allstdcodes.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;
            }
        });

       /* std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });*/

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=stdname.getEditText().getText().toString().trim();
                num2=stdcode.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(num1)){
                    Toast.makeText(StdCode.this, "Enter the field name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(num2)){
                    Toast.makeText(StdCode.this, "Enter the field name", Toast.LENGTH_SHORT).show();
                    return;
                }

                allstdcodes.add(num1+": "+num2);
                arrayAdapter.notifyDataSetChanged();
                AddData();
                stdname.getEditText().getText().clear();
                stdcode.getEditText().getText().clear();
            }
        });
    }

    private void setArrayList() {

                    Cursor res = db.getAllData();
                    if(res.getCount()==0)
                    {
                        Toast.makeText(StdCode.this,"Nothing to be displayed",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //while(res.moveToNext()) {

                        // allstdcodes.add(res.getString(0)+": "+res.getString(1));
                        if (res.moveToFirst()) {

                            do {
                                allstdcodes.add(/*"Name: " + firstName + ",Age: " + age*/res.getString(0) + ": " + res.getString(1));

                            } while (res.moveToNext());
                    }
    }

    public  void  AddData()
    {
                boolean isInserted=db.insertData(num1,num2);

                if(isInserted==true){
                    Toast.makeText(StdCode.this,"Data inserted",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(StdCode.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            }

    @Override
    protected void onStop() {
        allstdcodes.clear();
        super.onStop();
    }
}
