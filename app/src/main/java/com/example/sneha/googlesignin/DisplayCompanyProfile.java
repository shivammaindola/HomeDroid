package com.example.sneha.googlesignin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayCompanyProfile extends AppCompatActivity {

    //Initializations...
    TextView nameVenture, pan, tan, numDirector, cin, doi, hoaddress,
            bankName, accNumber, ifscCode, componyId, eStatementPw1, eStatementPw2, netBankingId, netBankingPw, mpin, vpa,
            gstinNumber, gstPortalNumber, gstPortalPw,
            mailId, mailPw,
            domainId, domainPw,
            railwayId, railwayPw, accHolderName, dob, emailId, phoneNumber,
            facebookId, facebookPw,
            amazonId, amazonPw,
            awsId, awsPw, awsIdParse, awsPwParse;
    LinearLayout layoutNameDirector, layoutDirectorId;
    String passnum;
    DatabaseHelper13 database;

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
                Intent intent = new Intent(this, EditCompanyProfile.class);
                intent.putExtra(AdapterClassCompany.KEY_INTENT_COMPANY, passnum);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_company_profile);

        // Initializations...
        nameVenture =  (TextView) findViewById(R.id.nameVenture);
        pan =  (TextView) findViewById(R.id.pan);
        tan =  (TextView) findViewById(R.id.tan);
        numDirector =  (TextView) findViewById(R.id.numDirector);
        bankName =  (TextView) findViewById(R.id.bankName);
        accNumber =  (TextView) findViewById(R.id.accNumber);
        ifscCode =  (TextView) findViewById(R.id.ifscCode);
        componyId =  (TextView) findViewById(R.id.componyId);
        eStatementPw1 =  (TextView) findViewById(R.id.eStatementPw1);
        eStatementPw2 =  (TextView) findViewById(R.id.eStatementPw2);
        netBankingId =  (TextView) findViewById(R.id.netBankingId);
        netBankingPw =  (TextView) findViewById(R.id.netBankingPw);
        mpin =  (TextView) findViewById(R.id.mpin);
        vpa =  (TextView) findViewById(R.id.vpa);
        gstinNumber =  (TextView) findViewById(R.id.gstinNumber);
        gstPortalNumber =  (TextView) findViewById(R.id.gstPortalNumber);
        gstPortalPw =  (TextView) findViewById(R.id.gstPortalPw);
        mailId =  (TextView) findViewById(R.id.mailId);
        mailPw =  (TextView) findViewById(R.id.mailPw);
        domainId =  (TextView) findViewById(R.id.domainId);
        domainPw =  (TextView) findViewById(R.id.domainPw);
        railwayId =  (TextView) findViewById(R.id.railwayId);
        railwayPw =  (TextView) findViewById(R.id.railwayPw);
        accHolderName =  (TextView) findViewById(R.id.accHolderName);
        dob =  (TextView) findViewById(R.id.dob);
        emailId =  (TextView) findViewById(R.id.emailId);
        phoneNumber =  (TextView) findViewById(R.id.phoneNumber);
        facebookId =  (TextView) findViewById(R.id.facebookId);
        facebookPw =  (TextView) findViewById(R.id.facebookPw);
        amazonId =  (TextView) findViewById(R.id.amazonId);
        amazonPw =  (TextView) findViewById(R.id.amazonPw);
        awsId =  (TextView) findViewById(R.id.awsId);
        awsPw =  (TextView) findViewById(R.id.awsPw);
        awsIdParse =  (TextView) findViewById(R.id.awsIdParse);
        awsPwParse =  (TextView) findViewById(R.id.awsPwParse);
        cin = (TextView) findViewById(R.id.cin);
        doi = (TextView) findViewById(R.id.doi);
        hoaddress = (TextView) findViewById(R.id.hoaddress);
        layoutDirectorId = (LinearLayout) findViewById(R.id.layoutDirectorId);
        layoutNameDirector = (LinearLayout) findViewById(R.id.layoutDirectorName);
        database  = new DatabaseHelper13(this);

        // Getting values from intent...
        Bundle bundle = getIntent().getExtras();
        passnum = bundle.getString(AdapterClassCompany.KEY_INTENT_COMPANY);

        //Getting dsta from database..
        Cursor res = database.getData(passnum);

        if(res.getCount() == 0){
            Toast.makeText(this, "Empty data fields", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()){
            nameVenture.setText(res.getString(0));
            pan.setText(res.getString(1));
            tan.setText(res.getString(2));
            numDirector.setText(res.getString(3));
            bankName.setText(res.getString(4));
            accNumber.setText(res.getString(5));
            ifscCode.setText(res.getString(6));
            componyId.setText(res.getString(7));
            eStatementPw1.setText(res.getString(8));
            eStatementPw2.setText(res.getString(9));
            netBankingId.setText(res.getString(10));
            netBankingPw.setText(res.getString(11));
            mpin.setText(res.getString(12));
            vpa.setText(res.getString(13));
            gstinNumber.setText(res.getString(14));
            gstPortalNumber.setText(res.getString(15));
            gstPortalPw.setText(res.getString(16));
            mailId.setText(res.getString(17));
            mailPw.setText(res.getString(18));
            domainId.setText(res.getString(19));
            domainPw.setText(res.getString(20));
            railwayId.setText(res.getString(21));
            railwayPw.setText(res.getString(22));
            accHolderName.setText(res.getString(23));
            dob.setText(res.getString(24));
            emailId.setText(res.getString(25));
            phoneNumber.setText(res.getString(26));
            facebookId.setText(res.getString(27));
            facebookPw.setText(res.getString(28));
            amazonId.setText(res.getString(29));
            amazonPw.setText(res.getString(30));
            awsId.setText(res.getString(31));
            awsPw.setText(res.getString(32));
            awsIdParse.setText(res.getString(33));
            awsPwParse.setText(res.getString(34));
            cin.setText(res.getString(35));
            doi.setText(res.getString(36));
            hoaddress.setText(res.getString(37));
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
