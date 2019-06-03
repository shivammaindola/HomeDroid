package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.widget.Toast.LENGTH_LONG;

public class EditSocial extends AppCompatActivity {
    DatabaseH mydb;
    EditText user, pass, secques, secans, recovery,holder;
    ImageView pass_show;
    boolean pass_check = false;

    AdapterClassSocial adapterClass2;
    Spinner s;
    String sitename, emailid;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.my_notees111:

                if (user.getText().toString().trim().equalsIgnoreCase("")) {
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
                        UpdateData();
                        finish();
                        return true;
                    }
                } else if (s.getSelectedItem().toString().equals("--Select Company Name--")) {
                    alertbox("Please Select Company");
                } else {
                    Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_edit_social);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "cambria.ttf", true);
        pass = (EditText) findViewById(R.id.pass);
        s = (Spinner) (findViewById(R.id.spinner));
        user = (EditText) findViewById(R.id.user);
        secques = (EditText) findViewById(R.id.secques);
        secans = (EditText) findViewById(R.id.secans);
        holder= (EditText) findViewById(R.id.holder);
        recovery = (EditText) findViewById(R.id.recovery);
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
//        CheckBox check = findViewById(R.id.check);
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//
//                if (checked) {
//                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                } else {
//                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.socialsites, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setPrompt("Gender");
        s.setAdapter(adapter);
        mydb = new DatabaseH(this);
        adapterClass2 = new AdapterClassSocial(SocialSites1.socialsitenamelist, SocialSites1.usernamelist, SocialSites1.passwordlist, EditSocial.this);


        Bundle bundle = getIntent().getExtras();
        sitename = bundle.getString("sitename");
        emailid = bundle.getString("mailaddress");
        Cursor res = mydb.GetTwoData(sitename, emailid);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            int n1 = Integer.parseInt(res.getString(6));
            s.setSelection(n1);
            user.setText(res.getString(1));
            pass.setText(res.getString(2));
            secques.setText(res.getString(3));
            secques.setText(res.getString(4));
            recovery.setText(res.getString(5));
            holder.setText(res.getString(7));


        }
    }

    public void UpdateData() {
        int n1 = s.getSelectedItemPosition();

        boolean isUpdated = mydb.update(s.getSelectedItem().toString(), user.getText().toString(), pass.getText().toString(), secques.getText().toString(), secans.getText().toString(),
                recovery.getText().toString(), String.valueOf(n1),holder.getText().toString(), sitename, emailid);
        if (isUpdated == true) {
            Toast.makeText(EditSocial.this, "Data is updated", LENGTH_LONG).show();
        } else
            Toast.makeText(EditSocial.this, "Data is not updated", LENGTH_LONG).show();
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
