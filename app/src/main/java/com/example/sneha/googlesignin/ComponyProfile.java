package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ComponyProfile extends AppCompatActivity {

    //Initializations...
    EditText nameVenture, pan, tan, numDirector, cin, hoaddress, doi,
            bankName, accNumber, ifscCode, componyId, eStatementPw1, eStatementPw2, netBankingId, netBankingPw, mpin, vpa,
            gstinNumber, gstPortalNumber, gstPortalPw,
            mailId, mailPw,
            domainId, domainPw,
            railwayId, railwayPw, accHolderName, dob, emailId, phoneNumber,
            facebookId, facebookPw,
            amazonId, amazonPw,
            awsId, awsPw, awsIdParse, awsPwParse;
    ImageView pass_show_estate1, pass_show_estate2, pass_show_netBank, pass_show_mpin, pass_show_gstPortal,
            pass_show_mail, pass_show_domain, pass_show_railway, pass_show_fb, pass_show_amazon, pass_show_aws, pass_show_awsParse;
    DatePickerDialog.OnDateSetListener onDateSetListener_dob, onDateSetListener_doi;
    ImageView pick_dob, pick_doi;
    boolean pass_estate1 = false;
    boolean pass_estate2 = false;
    boolean pass_netbank = false;
    boolean pass_mpin = false;
    boolean pass_gstPortal = false;
    boolean pass_mail = false;
    boolean pass_domain = false;
    boolean pass_railway = false;
    boolean pass_fb = false;
    boolean pass_amazon = false;
    boolean pass_aws = false;
    boolean pass_awsParse = false;
    ArrayList<String> directorNameList, directorIdList;
    ArrayList<EditText> directorName, directorId;
    LinearLayout layoutNameDirector, layoutDirectorId;
    SharedPreferences preferencesDirectorName, preferencesDirectorId;
    SharedPreferences.Editor editorDirectorName, editorDirectorId;
    DatabaseHelper13 database;


    // Toolbar...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpass, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Onclicking pass button in toolbar...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // Checking for any errors in mandatory fields....
        switch(item.getItemId()){
            case R.id.my_notees111:
                if(nameVenture.getText().toString().trim().equalsIgnoreCase("")){
                    nameVenture.setError("Enter Name of Venture");
                }else if(numDirector.getText().toString().trim().equalsIgnoreCase("")){
                    numDirector.setError("Enter Number of Directors");
                }else if(!(vpa.getText().toString().trim().equalsIgnoreCase(""))){
                   if(!vpa.getText().toString().contains("@")) {
                       vpa.setError("Incorrect VPA");
                   }
                }else if(!(mailId.getText().toString().trim().equalsIgnoreCase("")) && !mailId.getText().toString().contains("@") && !mailId.getText().toString().contains("@")){

                    mailId.setError("Incorrent Email");

                }else if(!(emailId.getText().toString().trim().equalsIgnoreCase(""))&& !emailId.getText().toString().contains("@") && !emailId.getText().toString().contains("@")){

                    emailId.setError("Incorrent Email");

                }else if(!pan.getText().toString().trim().equals("") && pan.getText().toString().length() != 10){

                    pan.setError("Incorrect PAN");

                }else if(!(tan.getText().toString().trim().equals("")) && tan.getText().toString().length() != 10){

                    tan.setError("Incorrect TAN");

                }else if(!(gstinNumber.getText().toString().trim().equals("")) && gstinNumber.getText().toString().length() != 15){
                        gstinNumber.setError("Incorrect GSTIN Number");
                }else if(!cin.getText().toString().equals("") && cin.getText().toString().length() != 21){
                    cin.setError("Incorrect CIN");
                }
                else {
                    insertData();
                    finish();
                }
            default:
                return false;
        }
    }

    // Insert Data...
    private void insertData() {
        boolean isInserted = database.insertData(
                nameVenture.getText().toString(),
                pan.getText().toString(),
                tan.getText().toString(),
                numDirector.getText().toString(),
                bankName.getText().toString(),
                accNumber.getText().toString(),
                ifscCode.getText().toString(),
                componyId.getText().toString(),
                eStatementPw1.getText().toString(),
                eStatementPw2.getText().toString(),
                netBankingId.getText().toString(),
                netBankingPw.getText().toString(),
                mpin.getText().toString(),
                vpa.getText().toString(),
                gstinNumber.getText().toString(),
                gstPortalNumber.getText().toString(),
                gstPortalPw.getText().toString(),
                mailId.getText().toString(),
                mailPw.getText().toString(),
                domainId.getText().toString(),
                domainPw.getText().toString(),
                railwayId.getText().toString(),
                railwayPw.getText().toString(),
                accHolderName.getText().toString(),
                dob.getText().toString(),
                emailId.getText().toString(),
                phoneNumber.getText().toString(),
                facebookId.getText().toString(),
                facebookPw.getText().toString(),
                amazonId.getText().toString(),
                amazonPw.getText().toString(),
                awsId.getText().toString(),
                awsPw.getText().toString(),
                awsIdParse.getText().toString(),
                awsPwParse.getText().toString(),
                cin.getText().toString(),
                doi.getText().toString(),
                hoaddress.getText().toString());

        int number = 0;
        try {
            number = Integer.parseInt(numDirector.getText().toString());
        }catch(Exception e){
            number = 0;
        }

        if(number != 0){
            for(int i=0; i<number; i++){
                directorNameList.add(directorName.get(i).getText().toString());
                directorIdList.add(directorId.get(i).getText().toString());
            }
        }

        if(isInserted){
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compony_profile);

        // Changing font to Cambria....
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        // Initializations...
        nameVenture =  (EditText) findViewById(R.id.nameVenture);
        pan =  (EditText) findViewById(R.id.pan);
        tan =  (EditText) findViewById(R.id.tan);
        numDirector =  (EditText) findViewById(R.id.numDirector);
        bankName =  (EditText) findViewById(R.id.bankName);
        accNumber =  (EditText) findViewById(R.id.accNumber);
        ifscCode =  (EditText) findViewById(R.id.ifscCode);
        componyId =  (EditText) findViewById(R.id.componyId);
        eStatementPw1 =  (EditText) findViewById(R.id.eStatementPw1);
        eStatementPw2 =  (EditText) findViewById(R.id.eStatementPw2);
        netBankingId =  (EditText) findViewById(R.id.netBankingId);
        netBankingPw =  (EditText) findViewById(R.id.netBankingPw);
        mpin =  (EditText) findViewById(R.id.mpin);
        vpa =  (EditText) findViewById(R.id.vpa);
        gstinNumber =  (EditText) findViewById(R.id.gstinNumber);
        gstPortalNumber =  (EditText) findViewById(R.id.gstPortalNumber);
        gstPortalPw =  (EditText) findViewById(R.id.gstPortalPw);
        mailId =  (EditText) findViewById(R.id.mailId);
        mailPw =  (EditText) findViewById(R.id.mailPw);
        domainId =  (EditText) findViewById(R.id.domainId);
        domainPw =  (EditText) findViewById(R.id.domainPw);
        railwayId =  (EditText) findViewById(R.id.railwayId);
        railwayPw =  (EditText) findViewById(R.id.railwayPw);
        accHolderName =  (EditText) findViewById(R.id.accHolderName);
        dob =  (EditText) findViewById(R.id.dob);
        emailId =  (EditText) findViewById(R.id.emailId);
        phoneNumber =  (EditText) findViewById(R.id.phoneNumber);
        facebookId =  (EditText) findViewById(R.id.facebookId);
        facebookPw =  (EditText) findViewById(R.id.facebookPw);
        amazonId =  (EditText) findViewById(R.id.amazonId);
        amazonPw =  (EditText) findViewById(R.id.amazonPw);
        awsId =  (EditText) findViewById(R.id.awsId);
        awsPw =  (EditText) findViewById(R.id.awsPw);
        awsIdParse =  (EditText) findViewById(R.id.awsIdParse);
        awsPwParse =  (EditText) findViewById(R.id.awsPwParse);
        doi = (EditText) findViewById(R.id.doi);
        hoaddress = (EditText) findViewById(R.id.hoaddress);
        cin = (EditText) findViewById(R.id.cin);
        layoutDirectorId = (LinearLayout) findViewById(R.id.layoutDirectorId);
        layoutNameDirector = (LinearLayout) findViewById(R.id.layoutNameDirector);
        pass_show_estate1 = (ImageView) findViewById(R.id.pass_show_estate1);
        pass_show_estate2 = (ImageView) findViewById(R.id.pass_show_estate2);
        pass_show_netBank = (ImageView) findViewById(R.id.pass_show_netBank);
        pass_show_mpin = (ImageView) findViewById(R.id.pass_show_mpin);
        pass_show_gstPortal = (ImageView) findViewById(R.id.pass_show_gstPortal);
        pass_show_mail = (ImageView) findViewById(R.id.pass_show_mail);
        pass_show_domain = (ImageView) findViewById(R.id.pass_show_domain);
        pass_show_railway = (ImageView) findViewById(R.id.pass_show_railway);
        pass_show_fb = (ImageView) findViewById(R.id.pass_show_fb);
        pass_show_amazon = (ImageView) findViewById(R.id.pass_show_amazon);
        pass_show_aws = (ImageView) findViewById(R.id.pass_show_aws);
        pass_show_awsParse = (ImageView) findViewById(R.id.pass_show_awsParse);
        pick_dob = (ImageView) findViewById(R.id.pick_dob);
        pick_doi = (ImageView) findViewById(R.id.pick_doi);
        database = new DatabaseHelper13(this);
        directorName = new ArrayList<>();
        directorId = new ArrayList<>();
        directorNameList = new ArrayList<>();
        directorIdList = new ArrayList<>();

        // Adding Director names field accoring to the number in "Number of Director" in Form...
        numDirector.addTextChangedListener(new TextWatcher() {

            int number = 0;
            String text_after = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                text_after = numDirector.getText().toString();

                // Checking if the text is integer or not..
                try{
                    number = Integer.parseInt(text_after);
                }catch (Exception e){
                    number = 0;
                    Toast.makeText(ComponyProfile.this, "Text is not Integer", Toast.LENGTH_SHORT).show();
                }
                putFields();
            }

            public void putFields(){
                layoutNameDirector.removeAllViews();
                layoutDirectorId.removeAllViews();


                // Expanding Edit texts for director names..
                for(int i=0; i<number; i++){
                    TextView textView = new TextView(ComponyProfile.this);
                    textView.setText((i+1) + ". Name of Director ");
                    textView.setTextSize(20);
                    layoutNameDirector.addView(textView);
                    EditText editText = new EditText(ComponyProfile.this);
                    directorName.add(editText);
                    layoutNameDirector.addView(editText);
                }

                // Expanding editTexts for director ID...
                for(int i=0; i<number; i++){
                    TextView textView = new TextView(ComponyProfile.this);
                    textView.setText((i+1) +". Director's ID ");
                    textView.setTextSize(20);
                    layoutDirectorId.addView(textView);
                    EditText editText =new EditText(ComponyProfile.this);
                    directorId.add(editText);
                    layoutDirectorId.addView(editText);
                }
            }
        });

        // On clicking pick dob...
        pick_dob.setOnClickListener(new View.OnClickListener() {

            DatePickerDialog dateDialog;

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {
                dateDialog = new DatePickerDialog(
                        ComponyProfile.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener_dob,
                        year , month , day);

                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });

        // On clicking pick doi...
        pick_doi.setOnClickListener(new View.OnClickListener() {

            DatePickerDialog dateDialog;

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {
                dateDialog = new DatePickerDialog(
                        ComponyProfile.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener_doi,
                        year , month , day);

                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });

        // PAN contraint..
        pan.addTextChangedListener(new TextWatcher() {

            String text_before = "";
            String text_after = "";
            int count_text = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_before = pan.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text_after = pan.getText().toString();

                // Increment count of text in pan...
                count_text = text_after.length();


                if(count_text != 0) {

                    if ((count_text <= 5 || count_text == 10) && Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                        pan.setText(text_before);

                    } else if (count_text > 5 && count_text < 10) {
                        if (!Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                            pan.setText(text_before);

                        }
                    } else if (count_text > 10) {

                        pan.setText(text_before);

                    }
                }

                pan.setSelection(pan.length());
            }
        });

        // TAN contraint..
        tan.addTextChangedListener(new TextWatcher() {

            String text_before = "";
            String text_after = "";
            int count_text = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_before = tan.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text_after = tan.getText().toString();

                // Increment count of text in pan...
                count_text = text_after.length();


                if(count_text != 0) {

                    if ((count_text <= 5 || count_text == 10) && Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                        tan.setText(text_before);

                    } else if (count_text > 5 && count_text < 10) {
                        if (!Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                            tan.setText(text_before);

                        }
                    } else if (count_text > 10) {

                        tan.setText(text_before);

                    }
                }

                tan.setSelection(tan.length());
            }
        });

        // GSTIN Number Contraints..
        gstinNumber.addTextChangedListener(new TextWatcher() {

            String text_before = "";
            String text_after = "";
            int count_text = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_before = gstinNumber.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text_after = gstinNumber.getText().toString();

                // Increment count of text in pan...
                count_text = text_after.length();


                if(count_text != 0) {

                    if(count_text <=2 && !Character.isDigit(text_after.charAt(text_after.length() - 1))){

                        gstinNumber.setText(text_before);

                    }else if (count_text > 2 && (count_text <= 7 || count_text == 12) && Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                        gstinNumber.setText(text_before);

                    } else if (count_text > 7 && count_text < 12) {
                        if (!Character.isDigit(text_after.charAt(text_after.length() - 1))) {

                            gstinNumber.setText(text_before);

                        }
                    } else if (count_text > 15) {

                        gstinNumber.setText(text_before);

                    }
                }

                gstinNumber.setSelection(gstinNumber.length());

            }
        });

        // Constraint for CIN...
        cin.addTextChangedListener(new TextWatcher() {

            int count_text = 0;
            String text_after = "";
            String text_before = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_before = cin.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text_after = cin.getText().toString();
                count_text = text_after.length();

                if(count_text != 0){

                    // Portion which should bw Character...
                    if((count_text == 1 || (count_text > 6 && count_text < 9) || (count_text>12 && count_text<16))
                            && Character.isDigit(text_after.charAt(text_after.length()-1))){

                        cin.setText(text_before);

                    // Portion which should be digits...
                    }else if(((count_text > 1 && count_text < 7) || (count_text >=9 && count_text < 13) || count_text >=16)
                            && !Character.isDigit(text_after.charAt(text_after.length() - 1))){

                        cin.setText(text_before);

                    }
                }

                cin.setSelection(text_after.length());
            }
        });


        // Updating Date of Birth...
        onDateSetListener_dob = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                String date = "";

                // Extracting today's date...
                int year_current = cal.get(Calendar.YEAR);
                int month_current = cal.get(Calendar.MONTH);
                int day_current = cal.get(Calendar.DAY_OF_MONTH);

                // Checking if the alarm date is not in past...
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                if(calendar.after(Calendar.getInstance())) {
                    alertbox("Date of Birth cannot exceed current date");
                    return;
                }else {
                    date = day + "-" + (month + 1) + "-" + year;
                }

                // Setting the date..
                doi.setText(date);
            }
        };

        // Updating Date of Incorporation...
        onDateSetListener_doi = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                String date = "";

                // Extracting today's date...
                int year_current = cal.get(Calendar.YEAR);
                int month_current = cal.get(Calendar.MONTH);
                int day_current = cal.get(Calendar.DAY_OF_MONTH);

                // Checking if the alarm date is not in past...
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                if(calendar.after(Calendar.getInstance())) {
                    alertbox("Date of Birth cannot exceed current date");
                    return;
                }else {
                    date = day + "-" + (month + 1) + "-" + year;
                }

                // Setting the date..
                dob.setText(date);
            }
        };

    }

    // Function to show the alert box....
    public void alertbox(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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




    // On Clicking Show password image....
    public void onClick_PassShow(View view) {

        switch(view.getId()){

            case R.id.pass_show_estate1:
                pass_estate1 = show_passowrd(pass_estate1, eStatementPw1, pass_show_estate1);
                break;
            case R.id.pass_show_estate2:
                pass_estate2 = show_passowrd(pass_estate2, eStatementPw2, pass_show_estate2);
                break;
            case R.id.pass_show_netBank:
                pass_netbank = show_passowrd(pass_netbank, netBankingPw, pass_show_netBank);
                break;
            case R.id.pass_show_mpin:
                pass_mpin = show_passowrd(pass_mpin, mpin, pass_show_mpin);
                break;
            case R.id.pass_show_gstPortal:
                pass_gstPortal = show_passowrd(pass_gstPortal, gstPortalPw, pass_show_gstPortal);
                break;
            case R.id.pass_show_mail:
                pass_mail = show_passowrd(pass_mail, mailPw, pass_show_mail);
                break;
            case R.id.pass_show_domain:
                pass_domain = show_passowrd(pass_domain, domainPw, pass_show_domain);
                break;
            case R.id.pass_show_railway:
                pass_railway = show_passowrd(pass_railway, railwayPw, pass_show_railway);
                break;
            case R.id.pass_show_fb:
                pass_fb = show_passowrd(pass_fb, facebookPw, pass_show_fb);
                break;
            case R.id.pass_show_amazon:
                pass_amazon = show_passowrd(pass_amazon, amazonPw, pass_show_amazon);
                break;
            case R.id.pass_show_aws:
                pass_aws = show_passowrd(pass_aws, awsPw, pass_show_aws);
                break;
            case R.id.pass_show_awsParse:
                pass_awsParse = show_passowrd(pass_awsParse, awsPwParse, pass_show_awsParse);
                break;
            default:
                Toast.makeText(this, "No ID Matched", Toast.LENGTH_SHORT).show();
        }

    }

    // Changing show pass image....
    public boolean show_passowrd(boolean pass_check_pass, EditText pass, ImageView pass_show){

        if(!pass_check_pass){
            pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            pass_show.setImageResource(R.drawable.pass_unshow);
            pass_check_pass = true;
        }else{
            pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
            pass_show.setImageResource(R.drawable.pass_show);
            pass_check_pass = false;
        }
        return pass_check_pass;
    }
}
