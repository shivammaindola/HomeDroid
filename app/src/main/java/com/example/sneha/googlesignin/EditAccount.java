package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditAccount extends AppCompatActivity{
    DatabaseHelper5 mydb;
    EditText number,holder,ifsc,nominee,relation,password;


    Button bank;
    static TextView bankview;
    static String bankname;
    ImageView pass_show;
    boolean pass_check = false;

    Spinner spinner;
    String passnum;
    AdapterClassAccount adapterClass2;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if(holder.getText().toString().trim().equalsIgnoreCase("")){
                    holder.setError("Enter Name");
                }

                else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Number");
                }
                else if(/*spinner.getSelectedItem().toString().equals("--Select your Bank--")*/bankview.getText().toString().equals(" ")){
                    alertbox("Please Select Bank");
                }
                else{
                UpdateData();
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
        setContentView(R.layout.activity_edit_account);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        mydb= new DatabaseHelper5(this);
        number=(EditText)findViewById(R.id.number);
        holder=(EditText)findViewById(R.id.holder);
        ifsc=(EditText)findViewById(R.id.ifsc);
        nominee=(EditText)findViewById(R.id.nominee);
        relation=(EditText)findViewById(R.id.relation);
        password=(EditText)findViewById(R.id.profile);
        spinner = (Spinner) findViewById(R.id.spinner);
        bankview=findViewById(R.id.bankview);
        bank=findViewById(R.id.bank);
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


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banklist, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Gender");
        spinner.setAdapter(adapter);
        adapterClass2 = new AdapterClassAccount(Account1.banknamelist, Account1.accountlist, EditAccount.this);
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("accountnumber");
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditAccount.this, BankName.class);
                i.putExtra("NAME", "editbank");
                startActivity(i);

            }
        });

        Cursor res = mydb.GetTwoData(passnum);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            int n1=Integer.parseInt(res.getString(7));
            spinner.setSelection(n1);
            bankview.setText(res.getString(0));
            number.setText(res.getString(1));
            holder.setText(res.getString(2));
            ifsc.setText(res.getString(3));
            nominee.setText(res.getString(4));
            relation.setText(res.getString(5));
            password.setText(res.getString(6));


        }
    }



    public void UpdateData() {

        int n1=spinner.getSelectedItemPosition();
        boolean isUpdated = mydb.update(bankname/*spinner.getSelectedItem().toString()*/, number.getText().toString(),
                holder.getText().toString(), ifsc.getText().toString(), nominee.getText().toString(), relation.getText().toString(), password.getText().toString(),String.valueOf(n1),passnum);
        if (isUpdated == true) {
            Toast.makeText(EditAccount.this, "Data is updated", LENGTH_LONG).show();

        } else
            Toast.makeText(EditAccount.this, "Data is not updated", LENGTH_LONG).show();
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
