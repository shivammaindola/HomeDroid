package com.example.sneha.googlesignin;

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

public class Mail extends AppCompatActivity {
    DatabaseH1 myDb;
    EditText mail, pass, phone, recovery, code, owner, user;
    ImageView pass_show;
    boolean pass_check = false;

    AdapterClassMail adapterClass1;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if (mail.getText().toString().trim().equalsIgnoreCase("")) {
                    mail.setError("Enter Email ID");
                } else if (pass.getText().toString().trim().equalsIgnoreCase("")) {
                    pass.setError("Enter Password");
                } else if (!mail.getText().toString().contains("@")) {
                    mail.setError("Invalid Email ID");
                }
//                else if (user.getText().toString().trim().equalsIgnoreCase("")) {
//                    user.setError("Enter User Name");
//                }
                else if (owner.getText().toString().trim().equalsIgnoreCase("")) {
                    owner.setError("Enter Owner Name");
                } else if (!(recovery.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (!recovery.getText().toString().contains("@")) {
                        recovery.setError("Invalid Email ID");
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
        setContentView(R.layout.activity_mail);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        myDb = new DatabaseH1(this);
        //code = (EditText) findViewById(R.id.code);
        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
        //user = (EditText) findViewById(R.id.user);
        owner = (EditText) findViewById(R.id.owner);
        pass_show = (ImageView) findViewById(R.id.pass_show);
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
//        CheckBox check = findViewById(R.id.check);
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (checked) {
//                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                } else {
//                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });

        phone = (EditText) findViewById(R.id.phone);
        recovery = (EditText) findViewById(R.id.recovery);
        adapterClass1 = new AdapterClassMail(Mail1.emaillist, Mail1.passwordlist, Mail.this);

    }

    public void AddData() {
        boolean isInserted = myDb.insertData(mail.getText().toString(),
                pass.getText().toString(), phone.getText().toString(), recovery.getText().toString(),owner.getText().toString());
        if (isInserted == true) {
            Toast.makeText(Mail.this, "Data inserted", Toast.LENGTH_LONG).show();
            Mail1.emaillist.add(mail.getText().toString().trim());
            Mail1.passwordlist.add(pass.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
        } else
            Toast.makeText(Mail.this, "Data is not inserted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}