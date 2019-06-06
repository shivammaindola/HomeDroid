package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Banking_card extends AppCompatActivity {
    DatabaseHelper4 mydb;
    ImageView pass_show_cvv, pass_show_mpin, pass_show_pin;
    boolean pass_check_cvv = false, pass_check_mpin = false, pass_check_pin = false;
    EditText holder, cvv, number, mpin, phoneno, emailid, exptext, fromtxt,atm;
    Button pick, pick2,bank,card;
    RadioButton debitradio,creditradio;
    RadioGroup rgroup;
    String pin, cvvno,atmpin;
    Spinner spinner, spinnerdebitcredit, spinnertype;
    AdapterClassBanking adapterClass2;
    String monthYearStr;
    String validfrom = "";
    long timeInMilliseconds;
    long time;
    String validthru = "",carddc;
    static String bankname,cardtype;
    static TextView bankview,cardview;

    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111: {

                cvvno = cvv.getText().toString();
                pin = mpin.getText().toString();
                atmpin=atm.getText().toString();
                int pinlength = pin.length();
                int cvvlength = cvvno.length();
                int atmlength=atmpin.length();
                if (number.getText().toString().trim().equalsIgnoreCase("")) {
                    number.setError("Enter Number");
                } else if (holder.getText().toString().trim().equalsIgnoreCase("")) {

                    holder.setError("Enter Name");
                } else if (exptext.getText().toString().trim().equalsIgnoreCase("")) {

                    exptext.setError("Enter Date");
                } else if (cvv.getText().toString().trim().equalsIgnoreCase("")) {

                    cvv.setError("Enter CVV");
                } else if (cvvlength != 3) {
                    cvv.setError("Enter 3 Digits");
                } else if (mpin.getText().toString().equalsIgnoreCase("")) {
                    mpin.setError("Enter MPIN");

                }else if (atmlength != 4) {
                   atm.setError("Enter 4 Digits");
                } else if (atm.getText().toString().equalsIgnoreCase("")) {
                    atm.setError("Enter PIN");
                } else if (!(emailid.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (!emailid.getText().toString().contains("@"))
                        emailid.setError("Invalid Email ID");
                    else {

                        AddData();
                        finish();
                        return true;
                    }
                } else if (/*spinner.getSelectedItem().toString().equals("--Select your Bank--")*/bankview.getText().toString().equals(" ")) {
                    alertbox("Please Select Bank");
                } else if (/*spinnerdebitcredit.getSelectedItem().toString().equals("--Select Card--")*/ !(debitradio.isChecked()||creditradio.isChecked())) {
                    alertbox("Please Select Card");
                } else if (/*spinnertype.getSelectedItem().toString().equals("--Select Card Type--")*/cardview.getText().toString().equals(" ")) {
                    alertbox("Please Select Card Type");
                } else if (!(mpin.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (pinlength != 4)
                        mpin.setError("Enter 4 digits");
                    else {
                        AddData();
                        finish();
                    }
                } else {

                    AddData();
                    finish();
                    return true;
                }
            }
            default:
                return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking_card);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        mydb = new DatabaseHelper4(this);
        number = (EditText) findViewById(R.id.number);
        holder = (EditText) findViewById(R.id.holder);
        mpin = (EditText) findViewById(R.id.mpin);
        cvv = (EditText) findViewById(R.id.cvv);
        exptext = (EditText) findViewById(R.id.exptext);
        emailid = (EditText) findViewById(R.id.emailid);
        phoneno = (EditText) findViewById(R.id.phoneno);
        pick = (Button) findViewById(R.id.pick);
        pick2 = (Button) findViewById(R.id.pickfrom);
        bank = (Button) findViewById(R.id.bank);
        card = (Button) findViewById(R.id.card);
        atm=findViewById(R.id.pin);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerdebitcredit = (Spinner) findViewById(R.id.debitcredit);
        spinnertype = (Spinner) findViewById(R.id.type);
//        CheckBox check = findViewById(R.id.check1);
//        CheckBox check2 = findViewById(R.id.check2);
//        CheckBox check3 = findViewById(R.id.check3);
        pass_show_cvv = (ImageView)findViewById(R.id.pass_show_cvv);
        pass_show_mpin = (ImageView)findViewById(R.id.pass_show_mpin);
        pass_show_pin = (ImageView)findViewById(R.id.pass_show_pin);
        fromtxt = findViewById(R.id.fromtext);
        debitradio=findViewById(R.id.debitradio);
        creditradio=findViewById(R.id.creditradio);
        rgroup=findViewById(R.id.radiodebitcredit);

        cardview=findViewById(R.id.typecard);
        bankview=findViewById(R.id.bankview);

        cardview.setVisibility(View.INVISIBLE);
        bankview.setVisibility(View.INVISIBLE);

        bankview.setText(" "); cardview.setText(" ");

        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(debitradio.isChecked())
                {
                    carddc=debitradio.getText().toString();
                }
                else
                    carddc=creditradio.getText().toString();

            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Banking_card.this, BankName.class);
                i.putExtra("NAME", "bankingcard");
                startActivity(i);

            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Banking_card.this, CardName.class);
                i.putExtra("NAME", "bankingcard");
                startActivity(i);
            }
        });
        pass_show_cvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check_cvv){
                    cvv.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show_cvv.setImageResource(R.drawable.pass_unshow);
                    pass_check_cvv = true;
                }else{
                    cvv.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show_cvv.setImageResource(R.drawable.pass_show);
                    pass_check_cvv = false;
                }
            }
        });

        pass_show_mpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check_mpin){
                    mpin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show_mpin.setImageResource(R.drawable.pass_unshow);
                    pass_check_mpin = true;
                }else{
                    mpin.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show_mpin.setImageResource(R.drawable.pass_show);
                    pass_check_mpin = false;
                }
            }
        });
        pass_show_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check_pin){
                    atm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show_pin.setImageResource(R.drawable.pass_unshow);
                    pass_check_pin = true;
                }else{
                    atm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show_pin.setImageResource(R.drawable.pass_show);
                    pass_check_pin = false;
                }
            }
        });
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (!checked) {
//                    cvv.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
//                } else {
//                    cvv.setInputType(InputType.TYPE_CLASS_NUMBER);
//                }
//            }
//        });
//        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (!checked) {
//                    mpin.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
//                } else {
//                    mpin.setInputType(InputType.TYPE_CLASS_NUMBER);
//                }
//            }
//        });
//        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (!checked) {
//                    atm.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
//                } else {
//                    atm.setInputType(InputType.TYPE_CLASS_NUMBER);
//                }
//            }
//        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banklist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Gender");
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.debitcredit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdebitcredit.setPrompt("Gender");
        spinnerdebitcredit.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertype.setPrompt("Gender");
        spinnertype.setAdapter(adapter3);

        adapterClass2 = new AdapterClassBanking(BankingCard1.banknamelist, BankingCard1.cardlist, Banking_card.this);



        pick.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        validthru = monthYearStr;
                        try {
                            java.util.Date mDate = input.parse(monthYearStr);
                            time = mDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        exptext.setText(formatMonthYear(monthYearStr));
                    }
                });
                pickerDialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");

            }
        });
        pick2.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;

                        try {
                            java.util.Date mDate = input.parse(monthYearStr);
                            timeInMilliseconds = mDate.getTime();
                            if (timeInMilliseconds >= time) {
                                alertbox("Can't exceed Valid Thru");
                            } else {
                                validfrom = monthYearStr;
                                fromtxt.setText(formatMonthYear(monthYearStr));
                            }


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                pickerDialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");

            }
        });


    }


    public void AddData() {
        int n1 = spinner.getSelectedItemPosition();
        int n2 = spinnerdebitcredit.getSelectedItemPosition();
        int n3 = spinnertype.getSelectedItemPosition();
        boolean isInserted = mydb.insertData(
                bankname, number.getText().toString(), holder.getText().toString(), validthru,
                cvv.getText().toString(), mpin.getText().toString(), phoneno.getText().toString(),
                emailid.getText().toString(), carddc/*spinnerdebitcredit.getSelectedItem().toString()*/, cardtype/*spinnertype.getSelectedItem().toString()*/,
                String.valueOf(n1), String.valueOf(n2), String.valueOf(n3), validfrom,atm.getText().toString());

        if (isInserted == true) {
            Toast.makeText(Banking_card.this, "Data inserted", Toast.LENGTH_LONG).show();
            BankingCard1.banknamelist.add(/*spinner.getSelectedItem().toString().trim()*/bankname);
            BankingCard1.cardlist.add(number.getText().toString().trim());
            adapterClass2.notifyDataSetChanged();

        } else
            Toast.makeText(Banking_card.this, "Data is not inserted", Toast.LENGTH_LONG).show();

    }


    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
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
    public void v(View v)
    {
        startActivity(new Intent(this,CardName.class));
    }
}
