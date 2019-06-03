package com.example.sneha.googlesignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;


public class OnlineSites extends AppCompatActivity {
    DatabaseOnline myDb;
    EditText account,mail,password,mobile,holder;
    ImageView pass_show;
    boolean pass_check = false;

    AdapterClassOnline adapterClass1;
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
                if( account.getText().toString().trim().equalsIgnoreCase("")){
                    account.setError("Enter Website Name");
                }
                else if(mail.getText().toString().trim().equalsIgnoreCase("")){
                    mail.setError("Enter Username");
                }else if(holder.getText().toString().trim().equalsIgnoreCase("")){
                    holder.setError("Enter Holder Name");
                }

                else if(password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Enter Password");
                }

                else{

                AddData();
                finish();
                return true;}
            default: return false;
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_online_sites);
        myDb= new DatabaseOnline(this);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        account=(EditText)findViewById(R.id.account);
        mail=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.pass);
        mobile=(EditText)findViewById(R.id.phone);
        holder=(EditText)findViewById(R.id.holder);
        pass_show = (ImageView)findViewById(R.id.pass_show);
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
//        CheckBox check=findViewById(R.id.check);
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if(checked){
//                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                }
//                else{
//                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });
        adapterClass1 = new AdapterClassOnline(OnlineSites1.accountlist, OnlineSites1.maillist, OnlineSites.this);
    }
    public  void  AddData()
    {
        boolean isInserted=myDb.insertData(account.getText().toString(),
                mail.getText().toString(),password.getText().toString(),mobile.getText().toString(),holder.getText().toString());
        if(isInserted==true){
            Toast.makeText(OnlineSites.this,"Data inserted",Toast.LENGTH_LONG).show();
            OnlineSites1.accountlist.add(account.getText().toString().trim());
            OnlineSites1.maillist.add(mail.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
        }
        else
            Toast.makeText(OnlineSites.this,"Data is not inserted",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}




