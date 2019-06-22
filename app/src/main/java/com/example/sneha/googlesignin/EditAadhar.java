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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditAadhar extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper3 myDb;
    EditText name,dob,address,number;
    ImageView btnAddData1,pickaadhar,pick11;
    static final int DATEPICKER_DIALOG_ID2=0;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    int key=0,count=0;
    String passnum;
    String p;
    int nolength;
    AdapterClassAadhar adapterClass1;
    //////////////////////
    int phoneLength;
    int countPhone = 0;
    String phoneNoNew = "";
    String phoneNoOld = "";
    EditText phoneNumber;
    /////////////////////

    DatabaseHelper3 dataHelper;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                p=number.getText().toString();
                ///////////////////////
                phoneLength = phoneNumber.getText().toString().length();
                ///////////////////////
                nolength=p.length();
                if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }
                else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Aadhaar Number");
                }
                else if(nolength!=15){
                    number.setError("Invalid Length");
                }
                //////////////////////////
                else if(phoneLength != 10){
                    phoneNumber.setError("Invalid Length");
                }
                else if(phoneNumber.getText().toString().trim().equalsIgnoreCase(""))
                {
                    phoneNumber.setError("Enter Phone Number");
                }
                //////////////////////////
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
        setContentView(R.layout.activity_edit_aadhar);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        name= (EditText)findViewById(R.id.name);
        address= (EditText)findViewById(R.id.address);
        dob = (EditText)findViewById(R.id.dob);
        number = (EditText)findViewById(R.id.number);
        pickaadhar=(ImageView)findViewById(R.id.pickaadhar);
        myDb = new DatabaseHelper3(this);
        adapterClass1 = new AdapterClassAadhar(aadhar1.aadharnamelist, aadhar1.aadharnumberlist, EditAadhar.this);

        //////////////////////////
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        /////////////////////////

        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("aadharcardnumber");
        dataHelper= new DatabaseHelper3(this);
        pickaadhar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditAadhar.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditAadhar.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 0;


            }
        });
        Log.e("Tag", passnum);
        Cursor res = dataHelper.GetTwoData(passnum);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            name.setText(res.getString(0));
            number.setText(res.getString(1));
            phoneNumber.setText(res.getString(2));
            dob.setText(res.getString(3));
            address.setText(res.getString(4));
        }

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (count <= number.getText().toString().length()
                        &&(number.getText().toString().length()==4
                        ||number.getText().toString().length()==9
                        ||number.getText().toString().length()==14)){
                    number.setText(number.getText().toString()+" ");
                    int pos = number.getText().length();
                    number.setSelection(pos);
                }else if (count >= number.getText().toString().length()
                        &&(number.getText().toString().length()==4
                        ||number.getText().toString().length()==9
                        ||number.getText().toString().length()==14)){
                    number.setText(number.getText().toString().substring(0,number.getText().toString().length()-1));
                    int pos = number.getText().length();
                    number.setSelection(pos);
                }
                count = number.getText().toString().length();
            }
        });

        ///////////////////////////////////
        //////////////////////////////////
    }
    public void UpdateData()
    {
        boolean isUpdated = myDb.updateData(
                name.getText().toString(),
                number.getText().toString(),
                phoneNumber.getText().toString(),
                dob.getText().toString(),
                address.getText().toString(),
                passnum);
        if(isUpdated){
            Toast.makeText(EditAadhar.this,"Data is updated", LENGTH_LONG).show();
        }
        else {
            Toast.makeText(EditAadhar.this, "Data is not updated", LENGTH_LONG).show();
        }
    }


    protected Dialog onCreateDialoug(int id){
        if(id== DATEPICKER_DIALOG_ID2){
            return  new DatePickerDialog(this,datePickerListener,year,month,day);
        }else
            return  null; }

    private  DatePickerDialog.OnDateSetListener datePickerListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year=i;
            month=i1;
            day=i2;
        }
    };

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;
        String day, month, year;
        year = Integer.toString(yearFinal);
        month = Integer.toString(monthFinal);
        day = Integer.toString(dayFinal);
        if (dayFinal < 10)
            day = "0" + day;
        if (monthFinal < 10)
            month = "0" + month;
        long time=System.currentTimeMillis();
//time=time+86400000;
        int k=0;
        long timeInMilliseconds =0;
        String Date = day+"-"+month+"-"+year;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date mDate = sdf.parse(Date);
            timeInMilliseconds = mDate.getTime();
            if(time>timeInMilliseconds)
                k=1;
            else
                k=0;

        } catch (ParseException e) {
            e.printStackTrace();
        }



        if (key == 0) {
            if (k==0)
            {
                alertbox("Date of Birth cannot exceed current Date");
                dob.setText(" ");
            }
            else
                dob.setText(day + "-" + month + "-" + year);
        }

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
