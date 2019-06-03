package com.example.sneha.googlesignin;

import android.database.Cursor;
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

import static android.widget.Toast.LENGTH_LONG;

public class EditWifi extends AppCompatActivity {

    DatabaseHelper7 myDb;
    EditText name,password;
    String passnum;
    boolean pass_check = false;
    ImageView pass_show;
    EditText wifiId;

    AdapterClassWifi adapterClass1;

    DatabaseHelper7 dataHelper;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if(wifiId.getText().toString().trim().equalsIgnoreCase("")){
                    wifiId.setError("Enter Wifi ID");
                }
                else if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }
                else if(password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Enter  Password");
                }
                else{
                UpdateData();
                adapterClass1.notifyDataSetChanged();
               finish();
                return true;}
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_wifi);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        name= (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.pass);
        myDb = new DatabaseHelper7(this);
        adapterClass1 = new AdapterClassWifi(Wifipass1.wifinamelist, Wifipass1.wifipasslist, EditWifi.this);
        pass_show = (ImageView)findViewById(R.id.pass_show);
        //wifiId = (EditText)findViewById(R.id.wifi_id);

        pass_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show.setImageResource(R.drawable.pass_unshow);
                    pass_check = true;
                }else{
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show.setImageResource(R.drawable.pass_show);
                    pass_check = false;
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
         passnum = bundle.getString("wifiname");

        Cursor res = myDb.GetTwoData(passnum);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            name.setText(res.getString(0));
            password.setText(res.getString(1));
            wifiId.setText(res.getString(2));
        }
    }
    public  void UpdateData()
    {

        boolean isUpdated=myDb.update(
                name.getText().toString(),
                password.getText().toString(),
                wifiId.getText().toString(),
                passnum);

        if(isUpdated==true){
            Toast.makeText(EditWifi.this,"Data is updated", LENGTH_LONG).show();
        }
        else
            Toast.makeText(EditWifi.this,"Data is not updated", LENGTH_LONG).show();
    }
}
