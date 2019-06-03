package com.example.sneha.googlesignin;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;
import android.text.format.DateFormat;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class Remainder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    DatabaseHelper8 mydb;
    EditText name,date,des;
    Button submit8,update8,view8,pick,set;
    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    static final int DATEPICKER_DIALOG_ID=0;
    static final int TIMEPICKER_DIALOG_ID=1;
    final  static int RQS_1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_remainder);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        mydb= new DatabaseHelper8(this);
        name=(EditText)findViewById(R.id.name);
        date=(EditText)findViewById(R.id.date);

       des=(EditText)findViewById(R.id.description);
        pick=(Button)findViewById(R.id.pick);
        set=(Button)findViewById(R.id.set);
        submit8=(Button)findViewById(R.id.submit8);

        update8=(Button)findViewById(R.id.update8);

        view8=(Button)findViewById(R.id.view8);
        AddData();
        UpdateData();
        viewAll();
       pick.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Remainder.this,Remainder.this,year,month,day);
                datePickerDialog.show();

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent("com.example.sneha.googlesignin");
               // PendingIntent pendingIntent=PendingIntent.getBroadcast(Remainder.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
               // AlarmManager alarmManager=(AlarmManager)(this.getSystemServices(Context.ALARM_SERVICE));
                Intent intent=new Intent(getBaseContext(),AlarmReceiver.class);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getBaseContext(),RQS_1,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
               long mills=calendar.getTimeInMillis();
               alarmManager.set(AlarmManager.RTC_WAKEUP,mills,pendingIntent);
                Toast.makeText(Remainder.this, "Event Scheduled", Toast.LENGTH_SHORT).show();
            }
        });

    }

   protected Dialog onCreateDialoug(int id){
       if(id== DATEPICKER_DIALOG_ID){
           return  new DatePickerDialog(this,datePickerListener,year,month,day);
       }
       else if(id== TIMEPICKER_DIALOG_ID){
           return  new DatePickerDialog(Remainder.this, (DatePickerDialog.OnDateSetListener) timePickerListener,year,month,day);
       }else
           return  null;

   }
   private  DatePickerDialog.OnDateSetListener datePickerListener=new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
           year=i;
           month=i1;
           day=i2;
           showDialog(TIMEPICKER_DIALOG_ID);

       }
   };
    private  TimePickerDialog.OnTimeSetListener timePickerListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hour=i;
            minute=i1;

        }
    } ;

    public  void  AddData()
    {
        //final Intent intent = new Intent(passport.this, nav.class);
        submit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb.insertData(
                        name.getText().toString(),date.getText().toString(),des.getText().toString());
                if(isInserted==true){
                    Toast.makeText(Remainder.this,"Data inserted", LENGTH_LONG).show();
                    //  Button mybutton=new Button(this);
                    //mybutton.setText("Passport");
                    // LinearLayout ll=(LinearLayout)findViewById(R.id.layoutforButton);
                    //LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    //ll.addView(mybutton,lp);
                    //intent.ad
                }
                else
                    Toast.makeText(Remainder.this,"Data is not inserted", LENGTH_LONG).show();
            }
        });

    }
    public  void UpdateData()
    {
        update8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=mydb.update( name.getText().toString(),date.getText().toString(),des.getText().toString());
                if(isUpdated==true){
                    Toast.makeText(Remainder.this,"Data is updated", LENGTH_LONG).show();

                }
                else
                    Toast.makeText(Remainder.this,"Data is not updated", LENGTH_LONG).show();
            }
        });
    }
    public void viewAll(){
        view8.setOnClickListener(new View.OnClickListener() {
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
                    buffer.append("name  :"+res.getString(0)+"\n");
                    buffer.append(" :"+res.getString(1)+"\n");


                    buffer.append("description  :"+res.getString(2)+"\n\n");


                   // buffer.append("des :"+res.getString(2)+"\n\n");
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
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal=i;
        monthFinal=i1;
        dayFinal=i2;
        Calendar calendar=Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(Remainder.this,Remainder.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal=i;
        minuteFinal=i1;
        date.setText("Date : "+dayFinal+ "-" +
                +(monthFinal+1) + "- " +
                ""+yearFinal + "\n " +
                "Time : "+hourFinal + ":" +
                ""+minuteFinal );

    }

   /* @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
     yearFinal=i;
     monthFinal=i1;
     dayFinal=i2;
     Calendar calendar=Calendar.getInstance();
     hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(Remainder.this,Remainder.this,hour,minute, DateFormat.is24HourFormat(this));
         timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
     hourFinal=i;
     minuteFinal=i1;
     date.setText("Date : "+dayFinal+ "-" +
             +(monthFinal+1) + "- " +
             ""+yearFinal + "\n " +
             "Time : "+hourFinal + ":" +
             ""+minuteFinal );

    }*/
}
