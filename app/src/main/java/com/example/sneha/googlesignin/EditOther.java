package com.example.sneha.googlesignin;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class EditOther extends AppCompatActivity {

    DatabaseHelper11 database;
    EditText question, answer;
    String passnum;
    String named;

    // Toolbar...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // On clicking button in toolbar...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.my_notees111:
                updatedata();
                finish();
                return true;
        }

        return false;
    }

    // Update data....
    private void updatedata() {
        boolean isUpdate = database.update(
                question.getText().toString(),
                answer.getText().toString(),
                named);

        if(isUpdate){
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show();
        }
    }

    // onCreate...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_other);

        // Initialization...
        question = (EditText) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        database = new DatabaseHelper11(this);

        // Changing font to Cambria....
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        //Getting data from Adapterclass...
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString(AdapterClassOther.KEY_INTENT);

        // Setting the default value...
        Cursor res = database.getTwoData(passnum);
        while(res.moveToNext()){
            named = res.getString(0);
            question.setText(res.getString(0));
            answer.setText(res.getString(1));
        }

    }
}
