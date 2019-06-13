package com.example.sneha.googlesignin;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
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

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Passport1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    DataHelper myDb;

    EditText holdername, passnum, place, issue, expiry;
    Button btnAddData, pick, pick1,image;
    Button reminderButton;
    static final int DATEPICKER_DIALOG_ID = 0;
    static final int DATEPICKER_DIALOG_ID1 = 1;
    NotificationHelper notificationHelper;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    int key = 0;


    private ImageView profileImageView;
    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private boolean hasImageChanged = false;
    Bitmap thumbnail;

    AdapterClass adapterClass1;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:

                if(holdername.getText().toString().trim().equalsIgnoreCase("")){
                    holdername.setError("Enter Holder Name");
                }
                else if(passnum.getText().toString().trim().equalsIgnoreCase("")){
                    passnum.setError("Enter Passport Number");
                }
                else{
                AddData();
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
        setContentView(R.layout.activity_passport1);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        holdername = (EditText) findViewById(R.id.editText_name);
        passnum = (EditText) findViewById(R.id.editText_num);
        place = (EditText) findViewById(R.id.editText_place);
        issue = (EditText) findViewById(R.id.editText_id);
        expiry = (EditText) findViewById(R.id.editText_id1);
        pick = (Button) findViewById(R.id.pick);
        pick1 = (Button) findViewById(R.id.pick1);
        reminderButton = (Button) findViewById(R.id.reminderButton);
        notificationHelper = new NotificationHelper(this);



        myDb = new DataHelper(this);
        adapterClass1 = new AdapterClass(Passport.passportnamelist, Passport.passportnumlist, Passport1.this);
        pick.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Passport1.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) Passport1.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 0;


            }
        });

        pick1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Passport1.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) Passport1.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 1;

            }
        });

        // On clicking Set Reminder for Renewal...
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Simple Notificatoin testing....
//                NotificationCompat.Builder nb = notificationHelper.getNotification("HomedRoid", "Hello I am here !!");
//                notificationHelper.getManager().notify(1, nb.build());

                Intent intent  = new Intent(Passport1.this, ReminderPassport.class);
                startActivity(intent);

            }
        });
    }

    //For first pick option
    protected Dialog onCreateDialoug(int id) {
        if (id == DATEPICKER_DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener, year, month, day);
        } else
            return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;
        }
    };

    // For second pick option
    protected Dialog onCreateDialoug1(int id) {
        if (id == DATEPICKER_DIALOG_ID1) {
            return new DatePickerDialog(this, datePickerListener, year, month, day);
        } else
            return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;
        }
    };

    //On Date set Listener
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
            Date mDate = sdf.parse(Date);
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
                alertbox("Date of Issue cannot exceed the current date");
                issue.setText(" ");
            }
            else
                issue.setText(day + "-" + month + "-" + year);
        }
        else if (key == 1)
        { String Date1 = issue.getText().toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date mDate = sdf1.parse(Date1);
               long issuetime= mDate.getTime();
                if(timeInMilliseconds<=issuetime)
                    k=1;
                else
                    k=0;

            } catch (ParseException e) {
                e.printStackTrace();
            }


            if (k==1)
            {
                alertbox("Date of Expiry cannot before the Date of Issue");
                expiry.setText(" ");
            }
            else
                expiry.setText(day + "-" + month + "-" + year);
        }
    }

    public void AddData() {

        boolean isInserted = myDb.insertData(holdername.getText().toString(),
                passnum.getText().toString(),
                place.getText().toString(), issue.getText().toString(), expiry.getText().toString());
        if (isInserted == true) {
            Toast.makeText(Passport1.this, "Data Inserted", Toast.LENGTH_LONG).show();
            Passport.passportnamelist.add(holdername.getText().toString().trim());
            Passport.passportnumlist.add(passnum.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
        } else
            Toast.makeText(Passport1.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // startActivity(new Intent(Account_details.this, Account.class));
        finish();
    }

}
