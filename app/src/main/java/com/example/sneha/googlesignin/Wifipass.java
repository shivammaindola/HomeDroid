package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Wifipass extends AppCompatActivity {
    DatabaseHelper7 myDb;
    EditText name,pass;
    ImageView pass_show;
    EditText wifiId;
    boolean pass_check = false;

    AdapterClassWifi adapterClass1;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees111 :

                if(wifiId.getText().toString().trim().equalsIgnoreCase("")){
                    wifiId.setError("Enter Wifi ID");
                }
                else if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }
                else if(pass.getText().toString().trim().equalsIgnoreCase("")){
                    pass.setError("Enter Password");
                }
                else{
                AddData();
                finish();
                return true;
                }
            default:
                return false;
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_wifipass);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        // Initializations...
        myDb= new DatabaseHelper7(this);
        name=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.pass);
        pass_show = (ImageView)findViewById(R.id.pass_show);
        wifiId = (EditText)findViewById(R.id.wifi_id);

        pass_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check){
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show.setImageResource(R.drawable.pass_unshow);
                    pass_check = true;
                }else{
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show.setImageResource(R.drawable.pass_show);
                    pass_check = false;
                }
            }
        });

        myDb = new DatabaseHelper7(this);
        adapterClass1 = new AdapterClassWifi(Wifipass1.wifinamelist, Wifipass1.wifipasslist, Wifipass.this);

    }
    public  void  AddData()
    {

        boolean isInserted=myDb.insertData(
                name.getText().toString(),
                pass.getText().toString(),
                wifiId.getText().toString());

        if(isInserted==true){
            Toast.makeText(Wifipass.this,"Data inserted",Toast.LENGTH_LONG).show();
            Wifipass1.wifinamelist.add(name.getText().toString().trim());
            Wifipass1.wifipasslist.add(pass.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
        }
        else
            Toast.makeText(Wifipass.this,"Data is not inserted",Toast.LENGTH_LONG).show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


