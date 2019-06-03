package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Account_details extends AppCompatActivity {
    DatabaseHelper5 mydb;
    EditText number, holder, ifsc, nominee, relation, password;

    Button bank;
    Spinner spinner;
    static TextView bankview;
    AdapterClassAccount adapterClass2;
    static String bankname;
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
                if (holder.getText().toString().trim().equalsIgnoreCase("")) {
                    holder.setError("Enter Name");
                } else if (number.getText().toString().trim().equalsIgnoreCase("")) {
                    number.setError("Enter Number");
                } else if (/*spinner.getSelectedItem().toString().equals("--Select your Bank--")*/bankview.getText().toString().equals(" ")) {
                    alertbox("Please Select Bank");
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
        setContentView(R.layout.activity_account_details);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);

        mydb = new DatabaseHelper5(this);
        number = (EditText) findViewById(R.id.number);
        holder = (EditText) findViewById(R.id.holder);
        ifsc = (EditText) findViewById(R.id.ifsc);
        nominee = (EditText) findViewById(R.id.nominee);
        relation = (EditText) findViewById(R.id.relation);
        password = (EditText) findViewById(R.id.profile);
        spinner = (Spinner) findViewById(R.id.spinner);
        bank = findViewById(R.id.bank);
        bankview=findViewById(R.id.bankview);
        bankview.setVisibility(View.INVISIBLE);
        bankview.setText(" ");
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banklist, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("--Select your Bank--");

        spinner.setAdapter(adapter);

        adapterClass2 = new AdapterClassAccount(Account1.banknamelist, Account1.accountlist, Account_details.this);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Account_details.this, BankName.class);
                i.putExtra("NAME", "bank");
                startActivity(i);

            }
        });


    }

    public void AddData() {
        int n1 = spinner.getSelectedItemPosition();
        boolean isInserted = mydb.insertData(
                /*spinner.getSelectedItem().toString()*/bankname, number.getText().toString(),
                holder.getText().toString(), ifsc.getText().toString(), nominee.getText().toString(), relation.getText().toString(), password.getText().toString(), String.valueOf(n1));
        if (isInserted == true) {
            Toast.makeText(Account_details.this, "Data inserted", Toast.LENGTH_LONG).show();
            Account1.banknamelist.add(/*spinner.getSelectedItem().toString().trim()*/bankname);
            Account1.accountlist.add(number.getText().toString().trim());
            adapterClass2.notifyDataSetChanged();
        } else
            Toast.makeText(Account_details.this, "Data is not inserted", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

    public void a(View v) {
        startActivity(new Intent(Account_details.this, BankName.class));
    }
}
