package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.session.PlaybackState;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Other extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton floatingActionButton ;
    DatabaseHelper11 mydb;
    EditText question,answer, formName;
    View editQues;
    View editAnswer;
    Button update,view,submit;
    LinearLayout lb;
    int count = 0;

    ArrayList<DatabaseHelper11> arrayDatabase;
    String NAME_PREF = "Shared_preference_for_other_module";
    String JSON = "Key_for_json_string";

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
                insertData(question.getText().toString(), answer.getText().toString(), formName.getText().toString());
                for(int i=0; i<count; i+=2){
                    editAnswer = lb.getChildAt(i);
                    editQues = lb.getChildAt(i);
                    EditText q = editQues.findViewById(R.id.editQues);
                    EditText a = editAnswer.findViewById(R.id.editAnswer);
                    insertData(q.getText().toString(), a.getText().toString(), formName.getText().toString());
                }
                finish();
                return true;
        }

        return false;
    }

    private void insertData(String ques, String ans, String form) {
        boolean isInserted = mydb.insertData(ques, ans, form);
        if(isInserted==true){
            Toast.makeText(Other.this,"Data inserted",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(Other.this,"Data is not inserted",Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_other);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        mydb= new DatabaseHelper11(this);
        question=(EditText)findViewById(R.id.question);
        answer=(EditText)findViewById(R.id.answer);
        formName = (EditText) findViewById(R.id.formName);
        editQues = new EditText(this);
        editAnswer = new EditText(this);
        submit=(Button)findViewById(R.id.submit);
        lb=(LinearLayout)findViewById(R.id.lb);
        update=(Button)findViewById(R.id.update);

        //view = (Button)findViewById(R.id.view);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);


//        loadData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=(LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final  View addView=layoutInflater.inflate(R.layout.row,null);
                lb.addView(addView);
                count++;
            }
        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isInserted = mydb.insertData(question.getText().toString(),answer.getText().toString());
//
////                SharedPreferences preferences = getSharedPreferences(NAME_PREF, MODE_PRIVATE);
////                SharedPreferences.Editor editor = preferences.edit();
////                Gson gson = new Gson();
////                String json = gson.toJson(arrayDatabase);
////                editor.putString(JSON, json);
////                editor.apply();
//
//                if(isInserted==true){
//                    Toast.makeText(Other.this,"Data inserted",Toast.LENGTH_LONG).show();
//                }
//                else
//                    Toast.makeText(Other.this,"Data is not inserted",Toast.LENGTH_LONG).show();
//            }
//        });

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isUpdated=mydb.update(question.getText().toString(),answer.getText().toString()
//                );
//                if(isUpdated==true){
//                    Toast.makeText(Other.this,"Data is updated",Toast.LENGTH_LONG).show();
//
//                }
//                else
//                    Toast.makeText(Other.this,"Data is not updated",Toast.LENGTH_LONG).show();
//            }
//        });
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = mydb.getAllData();
//                if(res.getCount()==0)
//                {
//                    showMessage("Error","Nothing found");
//                    return;
//                }
//                StringBuffer buffer=new StringBuffer();
//                while(res.moveToNext()){
//                    buffer.append(""+res.getString(0)+"\n");
//                    buffer.append(""+res.getString(1)+"\n\n");
//
//
//
//                }
//                showMessage("Data",buffer.toString());
//            }
//        });

    }
    public void AlertBox(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void loadData(){

        // loading the database array...
        SharedPreferences preferences = getSharedPreferences(NAME_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json_string = preferences.getString(JSON, null);
        Type type = new TypeToken<ArrayList<DatabaseHelper11>>() {}.getType();
        arrayDatabase = gson.fromJson(json_string, type);
        if(arrayDatabase == null){
            arrayDatabase = new ArrayList<>();
        }
    }
}
