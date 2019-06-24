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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditGas extends AppCompatActivity {
    DatabaseGas myDb;
    EditText name, number, phone, password, email, address, username;
    ImageView pass_show;
    boolean pass_check = false;
    String connectionno;

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
                } else if (!(email.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (!email.getText().toString().contains("@") || !email.getText().toString().contains(".")) {
                        email.setError("Invalid Email ID");
                    } else {
                        UpdateData();
                        finish();
                        return true;
                    }
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
        setContentView(R.layout.activity_edit_gas);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        phone = (EditText) findViewById(R.id.phone);
        username = findViewById(R.id.username);
        address = (EditText) findViewById(R.id.address);
        password = (EditText) findViewById(R.id.password);
        email = findViewById(R.id.email);
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
        myDb = new DatabaseGas(this);
        adapterClass1 = new AdapterClassGas(Gas1.Name, Gas1.Number, EditGas.this);

        Bundle bundle = getIntent().getExtras();
        connectionno= bundle.getString("connectionno");

        Cursor res = myDb.GetTwoData(connectionno);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            name.setText(res.getString(1));
            number.setText(res.getString(0));
            address.setText(res.getString(2));
            username.setText(res.getString(3));
            password.setText(res.getString(4));
            phone.setText(res.getString(5));
            email.setText(res.getString(6));


        }
    }

    public void UpdateData() {

        boolean isUpdated = myDb.updateData(number.getText().toString(), name.getText().toString(),address.getText().toString(), username.getText().toString()
                , password.getText().toString(), phone.getText().toString(),email.getText().toString(), connectionno);
        if (isUpdated == true) {
            Toast.makeText(EditGas.this, "Data is updated", LENGTH_LONG).show();

        } else
            Toast.makeText(EditGas.this, "Data is not updated", LENGTH_LONG).show();
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
