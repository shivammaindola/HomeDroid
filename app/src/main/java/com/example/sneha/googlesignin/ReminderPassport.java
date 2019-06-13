package com.example.sneha.googlesignin;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ReminderPassport extends AppCompatActivity {

    TextView reminderText_date, reminderText_time;
    Button setTime, setDate, cancelReminder, setReminder;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    SharedPreferences preferences;
    final static String SAVE_REMINDER_PASSPORT = "Saved_reminder_for_passport";
    final static String NO_REMINDER = "No Reminder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_passport);

        // Changing font to Cambria....
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        // Initialization...
        reminderText_date = (TextView) findViewById(R.id.reminderText_date);
        setTime = (Button) findViewById(R.id.setTime);
        setDate = (Button) findViewById(R.id.setDate);
        cancelReminder = (Button) findViewById(R.id.cancel);
        setReminder = (Button) findViewById(R.id.setReminder);
        reminderText_time = (TextView) findViewById(R.id.reminderText_time);
        final Calendar calendar = Calendar.getInstance();

        // Initializing calendar 12:00...
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        //Set reminder Text..
        setReminderText();

        // On click set time....
        setTime.setOnClickListener(new View.OnClickListener() {

            TimePickerDialog timeDialog;


            @Override
            public void onClick(View view) {
                timeDialog = new TimePickerDialog(
                        ReminderPassport.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onTimeSetListener,
                        12, 0, false);
                timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timeDialog.show();
            }
        });

        // On click set Date....
        setDate.setOnClickListener(new View.OnClickListener() {

            DatePickerDialog dateDialog;

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {
                dateDialog = new DatePickerDialog(
                        ReminderPassport.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener,
                        year + 1, month , day);

                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });

        // On Timepicked...
        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                // Setting time instances to the hour and minute of the users choice...
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                // Setting Text..
                String min = Integer.toString(minute);
                if(minute < 10) {
                    min = "0" + minute;
                }

                String time = hour + " : " + min;
                reminderText_time.setText(time);

                if(reminderText_date.getText().toString().equals(NO_REMINDER)){
                    reminderText_date.setText("");
                }
            }
        };

        // On Datepicked...
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                // Setting time instances to the hour and minute of the users choice...
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);


                // Setting Text..
                String date = day + "-" + (month+1) + "-" + year;
                reminderText_date.setText(date);

                if(reminderText_time.getText().toString().equals(NO_REMINDER)){
                    reminderText_time.setText("");
                }
            }
        };

        // Onclick set reminder...
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Checking if the alarm date is not in past...
                if(calendar.before(Calendar.getInstance())) {
                    alertbox("Date or Time can not be in past");
                    reminderText_date.setText(NO_REMINDER);
                    reminderText_time.setText("");
                    return;
                }

                // Setting up the alarm...
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(ReminderPassport.this, AlarmReceiverPassport.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ReminderPassport.this, 1, intent, 0);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (alarmManager != null) {

                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        Toast.makeText(ReminderPassport.this, "Reminder is set", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(ReminderPassport.this, "Reminder is not set", Toast.LENGTH_SHORT).show();
                    }
                }

                // Saving data..
                SharedPreferences.Editor editor = getSharedPreferences(SAVE_REMINDER_PASSPORT, MODE_PRIVATE).edit();
                editor.putInt("time_hour", hour);
                editor.putInt("time_minute", minute);
                editor.putInt("date_year", year);
                editor.putInt("date_month", month);
                editor.putInt("date_day", day);
                editor.apply();


                // Setting text...
                setReminderText();

            }
        });


        // Onclicking CANCEL REMINDER...
        cancelReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Setting up the alarm...
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(ReminderPassport.this, AlarmReceiverPassport.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ReminderPassport.this, 1, intent, 0);


                // Cancel alarm....
                if (alarmManager != null) {

                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(ReminderPassport.this, "Reminder Cancelled", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(ReminderPassport.this, "No Reminders to cancel", Toast.LENGTH_SHORT).show();
                }

                // Changing the text...
                reminderText_date.setText(NO_REMINDER);
                reminderText_time.setText("");


                // Removing Shared Preferences...
                SharedPreferences.Editor editor = getSharedPreferences(SAVE_REMINDER_PASSPORT, MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();

            }
        });

    }

    public void setReminderText(){
        // Retriving the saved data...
        preferences = getSharedPreferences(SAVE_REMINDER_PASSPORT, MODE_PRIVATE);
        int hour = preferences.getInt("time_hour", 0);
        int minute = preferences.getInt("time_minute", 0);
        int year = preferences.getInt("date_year", 0);
        int month = preferences.getInt("date_month", 0);
        int day = preferences.getInt("date_day", 0);

        String min = Integer.toString(minute);
        if(minute < 10) {
            min = "0" + minute;
        }

        // Checking if the date is passed...
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        if(cal.before(Calendar.getInstance())){
            reminderText_date.setText(NO_REMINDER);
            reminderText_time.setText("");
            return;
        }

        // If date is not passed...
        String text_date = "";
        String text_time = "";
        text_date += (day + "-" + (month+1) + "-" + year);
        text_time += (hour + " : " + min);

        reminderText_date.setText(text_date);
        reminderText_time.setText(text_time);
    }

    // To display alert box...
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
