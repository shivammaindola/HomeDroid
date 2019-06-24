package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditMail extends AppCompatActivity {

    DatabaseH1 myDb;
    EditText mail,pass,phone,recovery,owner, user;
    ImageView pass_show;
    boolean pass_check = false;
    AdapterClassMail adapterClass1;
    String passnum;
  //  DataHelper dataHelper;
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
                } else if (!mail.getText().toString().contains("@") && !mail.getText().toString().contains(".")) {
                    mail.setError("Invalid Email ID");
                }
//                else if (user.getText().toString().trim().equalsIgnoreCase("")) {
//                    user.setError("Enter User Name");
//                }
                else if (owner.getText().toString().trim().equalsIgnoreCase("")) {
                    owner.setError("Enter Owner Name");
                }
                else if(!(recovery.getText().toString().trim().equalsIgnoreCase(""))){
                    if (!recovery.getText().toString().contains("@") || !recovery.getText().toString().contains(".")) {
                        recovery.setError("Invalid Email ID");
                    }
                    else {
                        UpdateData();
                        finish();
                        return true;
                    }
                }
                else {
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
        setContentView(R.layout.activity_edit_mail);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        phone=(EditText)findViewById(R.id.phone);
        recovery=(EditText)findViewById(R.id.recovery);
        //user = (EditText) findViewById(R.id.user);
        owner = (EditText) findViewById(R.id.owner);

        pass_show = (ImageView)findViewById(R.id.pass_show);
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


        myDb = new DatabaseH1(this);
        adapterClass1 = new AdapterClassMail(Mail1.emaillist, Mail1.passwordlist, EditMail.this);
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("mailaddress");
        Cursor res = myDb.GetTwoData(passnum);
        if(res.getCount()==0)
        {
            Log.e("TAG", "Hello1");
            return;
        }
        while(res.moveToNext()){
            mail.setText(res.getString(0));
            pass.setText(res.getString(1));
            phone.setText(res.getString(2));
            recovery.setText(res.getString(3));
            owner.setText(res.getString(4));
        }

    }
    public  void UpdateData()
    {

        boolean isUpdated=myDb.updateData( mail.getText().toString(),pass.getText().toString(),phone.getText().toString(),
                recovery.getText().toString(),owner.getText().toString(),passnum);
        if(isUpdated==true){
            Toast.makeText(EditMail.this,"Data is updated", LENGTH_LONG).show();

        }
        else
            Toast.makeText(EditMail.this,"Data is not updated", LENGTH_LONG).show();
    }
}
