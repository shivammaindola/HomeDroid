package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SystemLogin extends AppCompatActivity {
    DatabaseSystem myDb;
    EditText name,username,password,hint;
    AdapterClassSystem adapterClass2;
    ImageView pass_show;
    boolean pass_check = false;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees111:

                if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }
                else if(username.getText().toString().trim().equalsIgnoreCase("")){
                    username.setError("Enter Username");
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
        setContentView(R.layout.activity_system_login);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        myDb= new DatabaseSystem(this);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        hint=(EditText)findViewById(R.id.hint);
        adapterClass2 = new AdapterClassSystem(SystemLogins1.Name, SystemLogins1.Username, SystemLogin.this);
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
//        CheckBox check = findViewById(R.id.check);
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (checked) {
//                   password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                } else {
//                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });


    }
    public  void  AddData() {
        boolean isInserted = myDb.insertData(name.getText().toString()
                , username.getText().toString(), password.getText().toString(),
                hint.getText().toString());
        if (isInserted == true) {
            Toast.makeText(SystemLogin.this, "Data Inserted", Toast.LENGTH_LONG).show();
            SystemLogins1.Name.add(name.getText().toString().trim());
            SystemLogins1.Username.add(username.getText().toString().trim());
            adapterClass2.notifyDataSetChanged();
        } else
            Toast.makeText(SystemLogin.this, "Data is not inserted", Toast.LENGTH_LONG).show();
    }
    public void alertbox(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(msg);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
