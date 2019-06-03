package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditInsurance extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatabaseInsurance myDb;
    EditText name,duration,plan,policyname,number,due,premium,holder;
    Button pick;
    Spinner s;

    static final int DATEPICKER_DIALOG_ID2=0;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    int key=0;

String name1,number1;
    AdatperClassInsurance adapterClass1;


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
                    name.setError("Enter Name");
                } else if(holder.getText().toString().trim().equalsIgnoreCase("")){
                    holder.setError("Enter Holder Name");
                }

                else if(number.getText().toString().trim().equalsIgnoreCase("")){
                    number.setError("Enter Number");
                }
                else{

                    UpdateData();finish();
                return true;}
            default:
                return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_insurance);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        myDb = new DatabaseInsurance(this);
        name = (EditText) findViewById(R.id.name);
        plan= (EditText) findViewById(R.id.plan);
        policyname = (EditText) findViewById(R.id.policyname);
        number= (EditText) findViewById(R.id.number);
        duration= (EditText) findViewById(R.id.duration);
        premium= (EditText) findViewById(R.id.premium);
        s=(Spinner)findViewById(R.id.frequency);
        due= (EditText) findViewById(R.id.due);
        pick= (Button) findViewById(R.id.pick);
        holder=findViewById(R.id.holder);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setPrompt("Gender");
        s.setAdapter(adapter);

        adapterClass1 = new AdatperClassInsurance(Insurance1.insurancenamelist, Insurance1.holdernamelist, EditInsurance.this);

        Bundle bundle = getIntent().getExtras();
         name1 = bundle.getString("name");
        number1= bundle.getString("number");
        myDb= new DatabaseInsurance(this);
        pick.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(EditInsurance.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, (DatePickerDialog.OnDateSetListener) EditInsurance.this,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show(); key = 0;

            }
        });
        Cursor res = myDb.GetTwoData(name1,number1);
        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext()){
            int n1=Integer.parseInt(res.getString(8));
            name.setText(res.getString(0));
            plan.setText(res.getString(1));
            policyname.setText(res.getString(2));
            number.setText(res.getString(3));
            duration.setText(res.getString(4));
            premium.setText(res.getString(5));
            s.setSelection(n1);
            due.setText(res.getString(7));
            holder.setText(res.getString(9));

        }
    }
    public  void UpdateData()
    {int n1=s.getSelectedItemPosition();

        boolean isUpdated=myDb.updateData(name.getText().toString(),plan.getText().toString(),policyname.getText().toString(),number.getText().toString(),duration.getText().toString(),premium.getText().toString(),s.getSelectedItem().toString(),due.getText().toString(),String.valueOf(n1),holder.getText().toString(),name1,number1);
        if(isUpdated==true){
            Toast.makeText(EditInsurance.this,"Data is updated", LENGTH_LONG).show();
        }
        else
            Toast.makeText(EditInsurance.this,"Data is not updated", LENGTH_LONG).show();
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
       /* long time=System.currentTimeMillis();
//time=time+86400000;
        int k=0;
        long timeInMilliseconds =0;
        String Date = day+"-"+month+"-"+year;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date mDate = sdf.parse(Date);
            timeInMilliseconds = mDate.getTime();
            if(time<timeInMilliseconds)
                k=1;
            else
                k=0;

        } catch (ParseException e) {
            e.printStackTrace();
        }
*/


        if (key == 0) {
           /* if (k==0)
            {
                alertbox("Invalid Date");
                pick.setText(" ");
            }
            else*/
            due.setText(day + "-" + month + "-" + year);
        }

    }
    public void alertbox(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(msg);
        builder.show();
    }
}