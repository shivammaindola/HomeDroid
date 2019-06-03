package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditDrivingLicense extends AppCompatActivity {

    // Initializations...
    EditText name, number, doi, validity, dob, father_name, address;
    DatePickerDialog.OnDateSetListener onDateSetListener_doi, onDateSetListener_validity, onDateSetListener_dob;
    String passnum;
    DatabaseHelper12 database;
    int day_doi, month_doi, year_doi;
    int day_validity, month_validity, year_validity;
    int day_dob, month_dob, year_dob;

    // Toolbar...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // Onclicking pass button in toolbar...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // Checking for any errors in mandatory fields....
        switch(item.getItemId()){
            case R.id.my_notees111:
                if(name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Number");
                }else if(doi.getText().toString().trim().equalsIgnoreCase("")){
                    doi.setError("Enter Date of Issue");
                }else if(validity.getText().toString().trim().equalsIgnoreCase("")){
                    validity.setError("Enter validity");
                }else if(number.getText().toString().length() != 16){
                    number.setError("Enter a valid DL number");
                }else{
                    updateData();
                    finish();
                    return true;
                }
            default:
                return false;
        }
    }

    // update Data Function...
    public void updateData(){
        boolean isUpdate = database.updateData(
                name.getText().toString(),
                number.getText().toString(),
                doi.getText().toString(),
                validity.getText().toString(),
                dob.getText().toString(),
                father_name.getText().toString(),
                address.getText().toString(),
                passnum);
        if(isUpdate){
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("tag", "Not updated");
            Toast.makeText(this,"Data is not updated", LENGTH_LONG).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_driving_license);

        // Changing font to Cambria....
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        //Getting data from Adapterclass...
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString(AdapterClassDrivingLicense.EDIT_DRIVING_CLICKED);

        // Initializations...
        database = new DatabaseHelper12(this);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        doi = (EditText) findViewById(R.id.doi);
        validity = (EditText) findViewById(R.id.validity);
        dob = (EditText) findViewById(R.id.dob);
        father_name = (EditText) findViewById(R.id.father_name);
        address = (EditText) findViewById(R.id.address);
        Cursor res = database.getData(passnum);

        // Initializing doi dates to today's date..
        Calendar cal = Calendar.getInstance();
        day_doi = cal.get(Calendar.DAY_OF_MONTH);
        month_doi = cal.get(Calendar.MONTH);
        year_doi = cal.get(Calendar.YEAR);

        month_doi = cal.get(Calendar.MONTH);
        month_validity = cal.get(Calendar.MONTH);
        month_dob = cal.get(Calendar.MONTH);

        year_doi = cal.get(Calendar.YEAR);
        year_validity = cal.get(Calendar.YEAR);
        year_dob = cal.get(Calendar.YEAR);

        // Updating Date of Issue...
        onDateSetListener_doi = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                String date = "";

                // Extracting today's date...
                int year_current = cal.get(Calendar.YEAR);
                int month_current = cal.get(Calendar.MONTH);
                int day_current = cal.get(Calendar.DAY_OF_MONTH);

                // Checking if date is not exceeding current date...
                if(year == year_current){
                    if(month == month_current){
                        if(day <= day_current){
                            date = day + "-" + (month + 1) + "-" + year ;
                        }
                    }else if(month < month_current){
                        date = day + "-" + (month + 1) + "-" + year ;
                    }
                }else if(year < year_current){
                    date = day + "-" + (month + 1) + "-" + year ;
                }
                if(date.equals("")){
                    alertbox("Date of Issue cannot exceed the current date");
                }

                // Setting integers for validity..
                day_doi = day;
                month_doi = month;
                year_doi = year;

                // Setting the date..
                doi.setText(date);
            }
        };

        // Updating validity...
        onDateSetListener_validity = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = "";

                // Checking if date is not before doi date...
                if(year == year_doi){
                    if(month == month_doi){
                        if(day >= day_doi){
                            date = day + "-" + (month + 1) + "-" + year ;
                        }
                    }else if(month > month_doi){
                        date = day + "-" + (month + 1) + "-" + year ;
                    }
                }else if(year > year_doi){
                    date = day + "-" + (month + 1) + "-" + year ;
                }
                if(date.equals("")){
                    alertbox("Validity cannot be before Date of issue");
                }

                // Setting the date..
                validity.setText(date);
            }
        };

        // Updating Date of Birth...
        onDateSetListener_dob = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                String date = "";

                // Extracting today's date...
                int year_current = cal.get(Calendar.YEAR);
                int month_current = cal.get(Calendar.MONTH);
                int day_current = cal.get(Calendar.DAY_OF_MONTH);


                // Checking DOI is filled or not.....
                if (doi.getText().toString().equals("")) {
                    // Checking if date is not exceeding current date...
                    if (year == year_current) {
                        if (month == month_current) {
                            if (day <= day_current) {
                                date = day + "-" + (month + 1) + "-" + year;
                            } else {
                                date = "";
                                alertbox("Date of Birth cannot exceed the current date");
                            }
                        } else if (month < month_current) {
                            date = day + "-" + (month + 1) + "-" + year;
                        } else {
                            date = "";
                            alertbox("Date of Birth cannot exceed the current date");
                        }
                    } else if (year < year_current) {
                        date = day + "-" + (month + 1) + "-" + year;
                    } else {
                        date = "";
                        alertbox("Date of Birth cannot exceed the current date");
                    }
                } else {
                    // Checking if date is not before doi date...
                    if (year == year_doi) {
                        if (month == month_doi) {
                            if (day <= day_doi) {
                                date = day + "-" + (month + 1) + "-" + year;
                            } else {
                                date = "";
                                alertbox("Date of Birth cannot exceed the date of issue");
                            }
                        } else if (month < month_doi) {
                            date = day + "-" + (month + 1) + "-" + year;
                        } else {
                            date = "";
                            alertbox("Date of Birth cannot exceed the date of issue");
                        }
                    } else if (year < year_doi) {
                        date = day + "-" + (month + 1) + "-" + year;
                    } else {
                        date = "";
                        alertbox("Date of Birth cannot exceed the date of issue");
                    }
                }

                // Setting the date..
                dob.setText(date);
            }
        };

        // Checking and correcting the DL number...
        number.addTextChangedListener(new TextWatcher() {

            int num_letter = 0;
            String text_before = "";
            String text_after = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_before = number.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                text_after = number.getText().toString();
                num_letter = text_after.length();

                if(num_letter <= 2 && num_letter !=0){
                    if(Character.isDigit(text_after.charAt(text_after.length() - 1))){
                        number.setText(text_before);
                        number.setSelection(text_after.length());
                    }
                }else if(text_before.length() == 5 && text_after.length() == 4){
                    number.setText(text_after.substring(0, number.getText().toString().length()-1));
                    number.setSelection(text_after.length());
                }
                else if(num_letter == 4){
                    number.setText(text_after + " ");
                    number.setSelection(text_after.length());
                }else if(num_letter > 2 && num_letter != 5){
                    if(!Character.isDigit(text_after.charAt(text_after.length() - 1))){
                        number.setText(text_before);
                        number.setSelection(text_after.length());
                    }
                }

            }
        });

        // Initializing all data according to previous value..
        if(res.getCount()==0)
        {
            Toast.makeText(this, "Empty data fields..", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()) {
            name.setText(res.getString(0));
            number.setText(res.getString(1));
            doi.setText(res.getString(2));
            validity.setText(res.getString(3));
            dob.setText(res.getString(4));
            father_name.setText(res.getString(5));
            address.setText(res.getString(6));
        }
    }



    // Onclick picking.....
    public void onClick_Pick(View view) {
        // Taking today's date....
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;

        // Checking the button clicked....
        switch (view.getId()){

            case R.id.pick_doi :
                dialog = new DatePickerDialog(EditDrivingLicense.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener_doi,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            case R.id.pick_validity:
                dialog = new DatePickerDialog(EditDrivingLicense.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener_validity,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            case R.id.pick_dob:
                dialog = new DatePickerDialog(EditDrivingLicense.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener_dob,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            default:
                Log.e("Tag", "Id did not match");
        }
    }

    // Function to show the alert box....
    public void alertbox(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
