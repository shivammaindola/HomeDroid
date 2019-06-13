package com.example.sneha.googlesignin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDrivingLicense extends AppCompatActivity {

    // Initialization..
    TextView name, number, doi, validity, dob, father_name, address;
    DatePickerDialog.OnDateSetListener onDateSetListener_doi, onDateSetListener_validity, onDateSetListener_dob;
    String passnum;
    DatabaseHelper12 database;


    // Initializing menu bar...
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pass, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // On clicking maenu button...
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees1 :
                Intent intent = new Intent(this, EditDrivingLicense.class);
                intent.putExtra(AdapterClassDrivingLicense.EDIT_DRIVING_CLICKED, passnum);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_driving_license);

        // Initialization..
        name = (TextView)findViewById(R.id.nameView);
        number = (TextView)findViewById(R.id.numberView);
        doi = (TextView)findViewById(R.id.doiView);
        validity  = (TextView)findViewById(R.id.validityView);
        dob = (TextView)findViewById(R.id.dobView);
        father_name = (TextView)findViewById(R.id.father_nameView);
        address = (TextView) findViewById(R.id.addressView);
        database = new DatabaseHelper12(this);
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString(AdapterClassDrivingLicense.EDIT_DRIVING_CLICKED);

        Cursor res = database.getData(passnum);

        if(res.getCount() == 0){
            Toast.makeText(this, "Empty data fields", Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()){
            name.setText(res.getString(0));
            number.setText(res.getString(1));
            doi.setText(res.getString(2));
            validity.setText(res.getString(3));
            dob.setText(res.getString(4));
            father_name.setText(res.getString(5));
            address.setText(res.getString(6));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
