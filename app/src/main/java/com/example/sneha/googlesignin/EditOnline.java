package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
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

import static android.widget.Toast.LENGTH_LONG;

public class EditOnline extends AppCompatActivity {
    DatabaseOnline myDb;
    EditText account, mail, password, mobile,holder;
    String passnum;
    AdapterClassOnline adapterClass1;
    ImageView pass_show;
    boolean pass_check = false;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if (account.getText().toString().trim().equalsIgnoreCase("")) {
                    account.setError("Enter Website Name");
                } else if(holder.getText().toString().trim().equalsIgnoreCase("")){
                    holder.setError("Enter Holder Name");
                }else if (mail.getText().toString().trim().equalsIgnoreCase("")) {
                    mail.setError("Enter Username");
                } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Enter Password");
                } else {
                    UpdateData();
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
        setContentView(R.layout.activity_edit_online);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        account = (EditText) findViewById(R.id.account);
        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pass);
        mobile = (EditText) findViewById(R.id.phone);
        holder= (EditText) findViewById(R.id.holder);
        myDb = new DatabaseOnline(this);
        adapterClass1 = new AdapterClassOnline(OnlineSites1.accountlist, OnlineSites1.maillist, EditOnline.this);
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
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("mailaddress");
        Cursor res = myDb.GetTwoData(passnum);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            account.setText(res.getString(0));
            mail.setText(res.getString(1));
            password.setText(res.getString(2));
            mobile.setText(res.getString(3));
            holder.setText(res.getString(4));

        }
    }

    public void UpdateData() {

        boolean isUpdated = myDb.updateData(account.getText().toString(), mail.getText().toString(), password.getText().toString(),
                mobile.getText().toString(),holder.getText().toString(), passnum);
        if (isUpdated == true) {
            Toast.makeText(EditOnline.this, "Data is updated", LENGTH_LONG).show();
        } else
            Toast.makeText(EditOnline.this, "Data is not updated", LENGTH_LONG).show();
    }
}
