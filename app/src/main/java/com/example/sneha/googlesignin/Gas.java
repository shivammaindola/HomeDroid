package com.example.sneha.googlesignin;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Gas extends AppCompatActivity {
    DatabaseGas myDb;
    EditText number, password, phone, email, name, address, username;
    ImageView pass_show;
    boolean pass_check = false;

    AdapterClassGas adapterClass1;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if (number.getText().toString().trim().equalsIgnoreCase("")) {
                    number.setError("Enter Number");
                } else if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Enter Name");
                }
                else if (!(email.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (!email.getText().toString().contains("@")) {
                        email.setError("Invalid Email ID");
                    } else {
                        AddData();
                        finish();
                        return true;
                    }
                } else {
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
        setContentView(R.layout.activity_gas);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        myDb = new DatabaseGas(this);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        address= (EditText) findViewById(R.id.address);
        number = (EditText) findViewById(R.id.number);
        password = (EditText) findViewById(R.id.password);
        username= (EditText) findViewById(R.id.username);
        email= (EditText) findViewById(R.id.email);
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
//                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                } else {
//                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });

        adapterClass1 = new AdapterClassGas(Gas1.Name,Gas1.Number, Gas.this);

    }

    public void AddData() {
        boolean isInserted = myDb.insertData(number.getText().toString(),
                name.getText().toString(), address.getText().toString(), username.getText().toString(),
                password.getText().toString(),phone.getText().toString(),email.getText().toString());
        if (isInserted == true) {
            Toast.makeText(Gas.this, "Data inserted", Toast.LENGTH_LONG).show();
            Gas1.Name.add(name.getText().toString().trim());
            Gas1.Number.add(number.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
        } else
            Toast.makeText(Gas.this, "Data is not inserted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}