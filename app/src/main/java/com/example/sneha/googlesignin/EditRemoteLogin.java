package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditRemoteLogin extends AppCompatActivity {
    DatabaseRemote mydb;
    EditText name,username,password;
    AdapterClassRemote adapterClass2;
    ImageView pass_show;
    boolean pass_check = false;
    String APPNAME;
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
                    name.setError("Enter App Name");
                }
                else if(username.getText().toString().trim().equalsIgnoreCase("")){
                    username.setError("Enter Login ID");
                }
                else if(password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Enter Password");
                }
                else{
                    UpdateData();
                    finish();
                    return true;}
            default: return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_remote_login);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        mydb= new DatabaseRemote(this);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        adapterClass2 = new AdapterClassRemote(RemoteLogin1.AppName, RemoteLogin1.Login, EditRemoteLogin.this);
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

        Bundle bundle = getIntent().getExtras();
        APPNAME = bundle.getString("appname");
        Cursor res = mydb.GetTwoData(APPNAME);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {

            username.setText(res.getString(1));
            password.setText(res.getString(2));
            name.setText(res.getString(0));


        }
    }

    public void UpdateData() {

        boolean isUpdated = mydb.updateData(name.getText().toString(),username.getText().toString(),password.getText().toString(),APPNAME);
        if (isUpdated == true) {
            Toast.makeText(EditRemoteLogin.this, "Data is updated", LENGTH_LONG).show();
        } else
            Toast.makeText(EditRemoteLogin.this, "Data is not updated", LENGTH_LONG).show();
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
}
