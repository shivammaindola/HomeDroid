package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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


public class SocialSites extends AppCompatActivity {
    DatabaseH mydb;
    EditText user, pass, secques, secans, recovery, holder;
    ImageView pass_show;
    boolean pass_check = false;

    AdapterClassSocial adapterClass2;
    static TextView pickSocial;
    Button selectSocial;
    static String socialName;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:

                if(pickSocial.getText().toString().equals("")){
                    pickSocial.setError("Select Compony");
                }
                else if (user.getText().toString().trim().equalsIgnoreCase("")) {
                    user.setError("Enter Username");
                } else if (pass.getText().toString().trim().equalsIgnoreCase("")) {
                    pass.setError("Enter Password");
                } else if (holder.getText().toString().trim().equalsIgnoreCase(""))
                    holder.setError("Enter Owner Name");
                else if (!(recovery.getText().toString().trim().equalsIgnoreCase(""))) {
                    if (!recovery.getText().toString().contains("@")) {
                        recovery.setError("Invalid Email ID");
                        Toast.makeText(this, "@", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
                        AddData();
                        finish();
                        return true;
                    }
                } else {
                    Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
                    AddData();
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
        setContentView(R.layout.activity_social_sites);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        mydb = new DatabaseH(this);
        pass = (EditText) findViewById(R.id.pass);
        selectSocial = (Button) findViewById(R.id.selectSocial);
        pickSocial = (TextView) findViewById(R.id.pickSocial);
        user = (EditText) findViewById(R.id.user);
        secques = (EditText) findViewById(R.id.secques);
        secans = (EditText) findViewById(R.id.secans);
        recovery = (EditText) findViewById(R.id.recovery);
        holder = findViewById(R.id.holder);
        pass_show = (ImageView)findViewById(R.id.pass_show);
        pass_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pass_check){
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_show.setImageResource(R.drawable.pass_unshow);
                    pass_check = true;
                }else{
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pass_show.setImageResource(R.drawable.pass_show);
                    pass_check = false;
                }
            }
        });

        selectSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(SocialSites.this, SocialMediaName.class);
                intent.putExtra("NAME", "socialSite");
                startActivity(intent);
            }
        });


        adapterClass2 = new AdapterClassSocial(SocialSites1.socialsitenamelist, SocialSites1.usernamelist, SocialSites1.passwordlist, SocialSites.this);

    }

    public void AddData() {
        //int n1 = s.getSelectedItemPosition();
        boolean isInserted = mydb.insertData("hello", user.getText().toString(),
                pass.getText().toString(), secques.getText().toString(),
                secans.getText().toString(), recovery.getText().toString(), socialName,holder.getText().toString());
        if (isInserted == true) {
            Toast.makeText(SocialSites.this, "Data inserted", Toast.LENGTH_LONG).show();
            SocialSites1.socialsitenamelist.add(pickSocial.getText().toString());
            SocialSites1.usernamelist.add(user.getText().toString().trim());
            SocialSites1.passwordlist.add(pass.getText().toString().trim());
            adapterClass2.notifyDataSetChanged();
        } else
            Toast.makeText(SocialSites.this, "Data is not inserted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
