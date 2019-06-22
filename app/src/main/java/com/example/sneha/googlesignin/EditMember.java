package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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

public class EditMember extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper6 myDb;
    EditText name, number, expiry, holder;
    ImageView pick1;
    static final int DATEPICKER_DIALOG_ID2 = 0;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    int key = 0;
    String passnum;

    AdapterClassMembership adapterClass1;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Enter Name");
                } else if (number.getText().toString().trim().equalsIgnoreCase("")) {
                    number.setError("Enter  Number");
                } else if (holder.getText().toString().trim().equalsIgnoreCase("")) {
                    holder.setError("Enter Name");
                } else {
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
        setContentView(R.layout.activity_edit_member);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        expiry = (EditText) findViewById(R.id.expiry);
        holder = findViewById(R.id.nameofholder);

        pick1 = (ImageView) findViewById(R.id.pickmember);
        myDb = new DatabaseHelper6(this);
        adapterClass1 = new AdapterClassMembership(membership1.membershipnamelist, membership1.membershipnumberlist, EditMember.this);

        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("membershipnumber");

        pick1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditMember.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditMember.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 0;

            }
        });
        Cursor res = myDb.GetTwoData(passnum);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            name.setText(res.getString(0));
            number.setText(res.getString(1));
            expiry.setText(res.getString(2));
            holder.setText(res.getString(3));


        }
    }

    public void UpdateData() {

        boolean isUpdated = myDb.update(name.getText().toString(), number.getText().toString(), expiry.getText().toString(), holder.getText().toString(), passnum);
        if (isUpdated == true) {
            Toast.makeText(EditMember.this, "Data is updated", LENGTH_LONG).show();

        } else
            Toast.makeText(EditMember.this, "Data is not updated", LENGTH_LONG).show();
    }

    protected Dialog onCreateDialoug(int id) {
        if (id == DATEPICKER_DIALOG_ID2) {
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
        long time = System.currentTimeMillis();
//time=time+86400000;
        int k = 0;
        long timeInMilliseconds = 0;
        String Date = day + "-" + month + "-" + year;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date mDate = sdf.parse(Date);
            timeInMilliseconds = mDate.getTime();
            if (time <= timeInMilliseconds)
                k = 1;
            else
                k = 0;

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (k == 0) {
            alertbox("Date of Expiry cannot before the current date");
            expiry.setText(" ");
        } else
            expiry.setText(day + "-" + month + "-" + year);


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