package com.example.sneha.googlesignin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class RequestFeature extends AppCompatActivity {

    String subject = "";
    String message = "";

    EditText edit_subject, edit_message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_feature);

        // Setting the font...
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        // Initialization...
        send = (Button) findViewById(R.id.button_send);
        edit_subject = (EditText) findViewById(R.id.edit_subject);
        edit_message = (EditText) findViewById(R.id.edit_message);

    }

    // On clicking the send button....
    public void sendEmail(View view) {

        subject = edit_subject.getText().toString();
        message = edit_message.getText().toString();

        String[] TO = {"homedroidtech@gmail.com"};
//        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RequestFeature.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
