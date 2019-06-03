package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.anwarshahriar.calligrapher.Calligrapher;

public class voterid extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    DatabaseHelper1 mydb;
    EditText number,name,father,dob,address,issue;
    Button submit1,up1 ,view1;

    Button btnAddData,pick,pick1;
    static final int DATEPICKER_DIALOG_ID=0;
    static final int DATEPICKER_DIALOG_ID1=1;
    // final  static int RQS_1=1;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    int key=0;


    AdapterClassVoter adapterClass1;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                if(name.getText().toString().trim().equalsIgnoreCase("")){
                     name.setError("Enter  Name");
                }
                else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Number");
                }
                else{
                AddData();
                /*Intent intent = new Intent(voterid.this, voterid1.class);
                //intent.putExtra("passportnum",pasnumber);
                startActivity(intent);*/
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
        setContentView(R.layout.activity_voterid);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        mydb= new DatabaseHelper1(this);
        number=(EditText)findViewById(R.id.number);
        name=(EditText)findViewById(R.id.name);
        father=(EditText)findViewById(R.id.father);
        dob=(EditText)findViewById(R.id.dob);
        address=(EditText)findViewById(R.id.address);
        issue=(EditText)findViewById(R.id.issue);
        pick=(Button)findViewById(R.id.pick);
        pick1=(Button)findViewById(R.id.pick1);
        // submit1=(Button)findViewById(R.id.submit1);
        //up1=(Button)findViewById(R.id.up1);

        // view1=(Button)findViewById(R.id.view1);

        // UpdateData();
        adapterClass1 = new AdapterClassVoter(voterid1.voternumlist, voterid1.voternamelist, voterid.this);
        // AddData();
        pick.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(voterid.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) voterid.this,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key=0;


            }
        });

        pick1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(voterid.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) voterid.this,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key=1;

            }
        });
    }
    protected Dialog onCreateDialoug(int id){
        if(id== DATEPICKER_DIALOG_ID){
            return  new DatePickerDialog(this,datePickerListener,year,month,day);
        }else
            return null;
    }

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
        if(id== DATEPICKER_DIALOG_ID1){
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

    public  void  AddData()
    {
        //final Intent intent = new Intent(passport.this, nav.class);
      /*  submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*/
        boolean isInserted = mydb.insertData(number.getText().toString()
                ,name.getText().toString(),father.getText().toString(),
                dob.getText().toString(),address.getText().toString(),issue.getText().toString());
        if(isInserted==true){
            Toast.makeText(voterid.this,"Data inserted",Toast.LENGTH_LONG).show();
            //Toast.makeText(voterid.this, "Data Inserted", Toast.LENGTH_LONG).show();
            voterid1.voternamelist.add(name.getText().toString().trim());
            voterid1.voternumlist.add(number.getText().toString().trim());
            adapterClass1.notifyDataSetChanged();
            //voterid1.adapterClass.notifyDataSetChanged();
        }
        else
            Toast.makeText(voterid.this,"Data is not inserted",Toast.LENGTH_LONG).show();
    }
    //  });



  /*  public void viewAll(){
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("voterid_number  :"+res.getString(0)+"\n");
                    buffer.append(" name    :"+res.getString(1)+"\n");
                    buffer.append("fathers_name  :"+res.getString(2)+"\n");
                    buffer.append("Dob   :"+res.getString(3)+"\n");
                    buffer.append("address  :"+res.getString(4)+"\n\n");
                    buffer.append("issue_date :"+res.getString(5)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/


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
                dob.setText(" ");
            }
            else
                dob.setText(day + "-" + month + "-" + year);
        }
        else if (key == 1)
        { String Date1 = dob.getText().toString();
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
                issue.setText(" ");
            }
            else  if(k==5){
                alertbox("Date of Issue cannot before Date of Birth");
                issue.setText(" ");
            }
            else
                issue.setText(day + "-" + month + "-" + year);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // startActivity(new Intent(Account_details.this, Account.class));
        finish();
    }
}
