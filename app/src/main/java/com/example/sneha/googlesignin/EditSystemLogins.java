package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditSystemLogins extends AppCompatActivity {
    DatabaseSystem mydb;
    EditText name,username,password,hint;
    AdapterClassSystem adapterClass2;
    ImageView pass_show;
    Button selectOS;
    static TextView textOS;
    static String osName;
    boolean pass_check = false;
    String SYSTEM,USER;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.my_notees111:

                if( name.getText().toString().trim().equalsIgnoreCase("")){
                    name.setError("Enter Name");
                }else if(textOS.getText().toString().equals("")){
                    alertbox("Select an Operating System");
                }
                else if(username.getText().toString().trim().equalsIgnoreCase("")){
                    username.setError("Enter Username");
                }
                else if(password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Enter Password");
                }
                else{
                    UpdateData();
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
        setContentView(R.layout.activity_edit_system_login);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        mydb= new DatabaseSystem(this);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        hint=(EditText)findViewById(R.id.hint);
        selectOS = (Button) findViewById(R.id.selectOS);
        textOS = (TextView) findViewById(R.id.textOS);
        adapterClass2 = new AdapterClassSystem(SystemLogins1.Name, SystemLogins1.Username, EditSystemLogins.this);
        pass_show = (ImageView)findViewById(R.id.pass_show);
        pass_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show.setImageResource(R.drawable.pass_unshow);
                    pass_check = true;
                }else{
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show.setImageResource(R.drawable.pass_show);
                    pass_check = false;
                }
            }
        });

        //On selecting SELECT..
        selectOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSystemLogins.this, OperatingSystemName.class);
                intent.putExtra("NAME", "EditSystemLogin");
                startActivity(intent);
            }
        });


        Bundle bundle = getIntent().getExtras();
        SYSTEM = bundle.getString("systemname");
        USER = bundle.getString("username");
        Cursor res = mydb.GetTwoData(SYSTEM);

        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            name.setText(res.getString(0));
            username.setText(res.getString(1));
            password.setText(res.getString(2));
            hint.setText(res.getString(3));
            textOS.setText(res.getString(4));
        }
    }

    public void UpdateData() {
        boolean isUpdated = mydb.updateData(
                name.getText().toString(),
                username.getText().toString(),
                password.getText().toString(),
                hint.getText().toString(),
                osName,
                SYSTEM,USER);
        if (isUpdated == true) {
            Toast.makeText(EditSystemLogins.this, "Data is updated", LENGTH_LONG).show();
        } else
            Toast.makeText(EditSystemLogins.this, "Data is not updated", LENGTH_LONG).show();
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
