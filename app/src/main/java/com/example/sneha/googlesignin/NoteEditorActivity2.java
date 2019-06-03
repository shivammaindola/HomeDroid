package com.example.sneha.googlesignin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.HashSet;

import me.anwarshahriar.calligrapher.Calligrapher;

public class NoteEditorActivity2 extends AppCompatActivity {
    EditText editText;
    int purchaseId;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);
        setContentView(R.layout.activity_note_editor2);
        editText = (EditText)findViewById(R.id.writehere);
        Intent intent = getIntent();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        purchaseId = intent.getIntExtra("purchaseId",-1);

        if(purchaseId!=(-1))
        {
            editText.setText(PurchaseList.notes1.get((purchaseId)));
        }
        else
        {
            PurchaseList.notes1.add("");
            purchaseId=PurchaseList.notes1.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PurchaseList.notes1.set(purchaseId,String.valueOf(s));
                PurchaseList.arrayAdapter8.notifyDataSetChanged();

                SharedPreferences sharedPreferences5 = getApplicationContext().getSharedPreferences("com.example.sneha.googlesignin", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(TodoNew.notes);
                sharedPreferences5.edit().putStringSet("purchase",set).apply();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
