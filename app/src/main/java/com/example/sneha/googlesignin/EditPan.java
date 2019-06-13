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
import android.text.InputFilter;
import android.text.TextWatcher;
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

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditPan extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper2 myDb;
    EditText name,father,dob,number;
    Button pick1;
    static final int DATEPICKER_DIALOG_ID2=0;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    int key=0;
    String p;
    int length;
    String regex = "\\d+";
    String regex2="^[a-zA-Z]*$";
    String passnum;
    AdapterClassPan adapterClass1;

    DatabaseHelper2 dataHelper;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:
                p= number.getText().toString();
                length=p.length();
                if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }
                else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Number");
                } else if(length!=10)
                {
                    number.setError("Invalid Number");
                }
                else if(!(p.substring(5,9)).matches(regex)||!(p.substring(0,5)).matches(regex2)||!(p.substring(9)).matches(regex2)){
                    number.setError("Invalid Number");
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
        setContentView(R.layout.activity_edit_pan);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        name= (EditText)findViewById(R.id.name);
        father = (EditText)findViewById(R.id.father);
        dob = (EditText)findViewById(R.id.dob);
        number = (EditText)findViewById(R.id.number);
        number.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        //  expiry1 = (EditText)findViewById(R.id.editText_id11);
        //btnAddData1 = (Button)findViewById(R.id.button_add1);
        pick1=(Button)findViewById(R.id.pick);
       // pick11=(Button)findViewById(R.id.pickit1);
        myDb = new DatabaseHelper2(this);
        adapterClass1 = new AdapterClassPan(pancard1.pancardnamelist, pancard1.pancardnumberlist, EditPan.this);
        //UpdateData();

        Bundle bundle = getIntent().getExtras();
         passnum = bundle.getString("pancardnumber");
        dataHelper= new DatabaseHelper2(this);
        pick1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPan.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditPan.this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                key = 0;


            }
        });
        Cursor res = dataHelper.GetTwoData(passnum);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            name.setText(res.getString(0));
            father.setText(res.getString(1));
            dob.setText(res.getString(2));
            number.setText(res.getString(3));
           // expiry1.setText(res.getString(4));

        }

        number.addTextChangedListener(new TextWatcher() {

            String text_before = "";
            String text_after = "";
            int count_text = 0;

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

                // Increment count of text in number...
                count_text = text_after.length();


                if(count_text != 0) {

                    if ((count_text <= 5 || count_text == 10) && Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                        number.setText(text_before);

                    } else if (count_text > 5 && count_text < 10) {
                        if (!Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                            number.setText(text_before);

                        }
                    } else if (count_text > 10) {

                        number.setText(text_before);

                    }
                }

                number.setSelection(number.length());
            }
        });

    }
    public  void UpdateData()
    {

        boolean isUpdated=myDb.updateData( name.getText().toString(),father.getText().toString(),dob.getText().toString(),number.getText().toString(),passnum);
        if(isUpdated==true){
            Toast.makeText(EditPan.this,"Data is updated", LENGTH_LONG).show();
        }
        else
            Toast.makeText(EditPan.this,"Data is not updated", LENGTH_LONG).show();
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
