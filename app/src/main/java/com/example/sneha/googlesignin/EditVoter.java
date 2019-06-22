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
import java.util.Date;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditVoter extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    DatabaseHelper1 myDb;
    EditText holdername1,passnum1,place1,issue1,expiry1,issue2;
    ImageView btnAddData1,pick1,pick11;
    static final int DATEPICKER_DIALOG_ID2=0;
    static final int DATEPICKER_DIALOG_ID3=1;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    int key=0;
    String passnum;

    AdapterClassVoter adapterClass1;

    DatabaseHelper1 dataHelper;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if(holdername1.getText().toString().trim().equalsIgnoreCase("")){
                    holdername1.setError("Enter  Name");
                }
                else if(passnum1.getText().toString().trim().equalsIgnoreCase("")){
                    passnum1.setError("Enter Number");
                }
                else{
                UpdateData();
                /*Intent intent = new Intent(EditVoter.this, voterid1.class);
                //intent.putExtra("passportnum",pasnumber);
                startActivity(intent); */finish();
                return true;}
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_voter);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        holdername1= (EditText)findViewById(R.id.name);
        passnum1 = (EditText)findViewById(R.id.number);
        place1 = (EditText)findViewById(R.id.father);
        issue1 = (EditText)findViewById(R.id.dob);
        expiry1 = (EditText)findViewById(R.id.address);
        issue2 = (EditText)findViewById(R.id.issue);
        //btnAddData1 = (Button)findViewById(R.id.button_add1);
        pick1=(ImageView)findViewById(R.id.pick);
        pick11=(ImageView)findViewById(R.id.pick1);
        myDb = new DatabaseHelper1(this);
        adapterClass1 = new AdapterClassVoter(voterid1.voternumlist, voterid1.voternamelist, EditVoter.this);
        //UpdateData();

        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString("voterid_number");
        dataHelper=new DatabaseHelper1(this);
        pick1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditVoter.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditVoter.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 0;


            }
        });

        pick11.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditVoter.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditVoter.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key=1;

            }
        });

        Cursor res = dataHelper.GetTwoData(passnum);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            passnum1.setText(res.getString(0));
            holdername1.setText(res.getString(1));
            place1.setText(res.getString(2));
            issue1.setText(res.getString(3));
            expiry1.setText(res.getString(4));
            issue2.setText(res.getString(5));

        }
    }
    public  void UpdateData()
    {
      /*  btnAddData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*/
        boolean isUpdated=myDb.updateData( passnum1.getText().toString(),holdername1.getText().toString(),place1.getText().toString(),issue1.getText().toString(),expiry1.getText().toString(),issue2.getText().toString(),passnum);
        if(isUpdated==true){
            Toast.makeText(EditVoter.this,"Data is updated", LENGTH_LONG).show();
           // startActivity(new Intent(EditVoter.this,Passport.class));

        }
        else
            Toast.makeText(EditVoter.this,"Data is not updated", LENGTH_LONG).show();
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
    // For second pick option
    protected Dialog onCreateDialoug1(int id){
        if(id== DATEPICKER_DIALOG_ID3){
            return  new DatePickerDialog(this,datePickerListener,year,month,day);
        }else
            return  null; }

    private  DatePickerDialog.OnDateSetListener datePickerListener1=new DatePickerDialog.OnDateSetListener() {
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
                alertbox("Date of Birth cannot exceed the current date");
                issue1.setText(" ");
            }
            else
                issue1.setText(day + "-" + month + "-" + year);
        }
        else if (key == 1)
        {String Date1 = issue1.getText().toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date mDate = sdf1.parse(Date1);
                long dobtime= mDate.getTime();
                if(dobtime<timeInMilliseconds)
                    k=1;
                else
                    k=5;

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(timeInMilliseconds>time)
                k=0;


            if (k==0)
            {
                alertbox("Date of Issue cannot exceed the current date");
                issue2.setText(" ");
            }
            else  if(k==5){
                alertbox("Date of Issue cannot before Date of Birth");
                issue2.setText(" ");
            }
            else
                issue2.setText(day + "-" + month + "-" + year);
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
