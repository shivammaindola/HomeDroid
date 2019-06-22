package com.example.sneha.googlesignin;

import android.app.KeyguardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ButtonPage1 extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    CardView sesitive,reminder,todolist,purchase,visiting,sticky,recharge,debit,bussiness,stdcode,goal;


    /////////////////////////////////////////////////////////////////////////////////////////
//    private DrawerLayout dl;
//    private ActionBarDrawerToggle t;
//    private NavigationView nv;
//    private GoogleApiClient googleApiClient;
//    private GoogleSignInOptions gso;
//    TextView userEmail,userName,label_madeWith, label_heart, label_byHomedroid;
//    ImageView userPic;
//    boolean doubleBackToExitPressedOnce = false;
//    private static final int LOCK_REQUEST_CODE = 221;
//    private static final int SECURITY_SETTING_REQUEST_CODE = 233;
    /////////////////////////////////////////////////////////////////////////////////////////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_button_page1);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        ////////////////////////////
//        authenticateApp();
        ///////////////////////////

        ///////////////////////////////////////////////////////////////////////
//        dl = (DrawerLayout) findViewById(R.id.drawer);
//        label_madeWith=findViewById(R.id.label_madeWith);
//        label_heart = findViewById(R.id.label_heart);
//        label_byHomedroid = findViewById(R.id.label_byHomedroid);
//        String s = new String(Character.toChars(0x2764));
//        label_madeWith.setText("Made with");
//        label_byHomedroid.setText("by Homedroid");
//        label_heart.setText(s);
//        label_heart.setTextColor(Color.RED);
        ///////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////
//        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
//        dl.addDrawerListener(t);
//        t.syncState();
        //////////////////////////////////////////////////////////////////////////////////

        sesitive=(CardView)findViewById(R.id.card1);
        reminder=(CardView)findViewById(R.id.card2);
        recharge=(CardView)findViewById(R.id.card3);
        debit=(CardView)findViewById(R.id.card4);
        todolist=(CardView)findViewById(R.id.card5);
        bussiness=(CardView)findViewById(R.id.card6);
        sticky =(CardView)findViewById(R.id.card7);
        visiting=(CardView)findViewById(R.id.card8);
        stdcode=(CardView)findViewById(R.id.card9);
        goal=(CardView)findViewById(R.id.card11);
        purchase=(CardView)findViewById(R.id.card12);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleApiClient=new GoogleApiClient.Builder(this)
//                .enableAutoManage(this,  this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();

//        nv = (NavigationView) findViewById(R.id.nv);
//        View headerView = nv.getHeaderView(0);
//        userEmail = (TextView) headerView.findViewById(R.id.user_id);
//        userName = (TextView) headerView.findViewById(R.id.user_name);
//        userPic = (ImageView) headerView.findViewById(R.id.user_pic);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.settings) {
//                    Toast.makeText(ButtonPage1.this, "Settings", Toast.LENGTH_SHORT).show();
//                } else if (id == R.id.req) {
//                    Toast.makeText(ButtonPage1.this, "Request a feature", Toast.LENGTH_SHORT).show();
//
//                } else if (id == R.id.logout) {
//                    FirebaseAuth.getInstance().signOut();
//                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
//                            new ResultCallback<Status>() {
//                                @Override
//                                public void onResult(Status status) {
//                                    if (status.isSuccess()){
//                                        gotoMainActivity();
//                                    }else{
//                                        Toast.makeText(getApplicationContext(),"Session not close",Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                    Toast.makeText(ButtonPage1.this, "Sign out", Toast.LENGTH_SHORT).show();
//
//
//                    return true;
//                }
//
//                return false;
//            }
//
//
//        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        sesitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,allsensitive.class));
            }
        });
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,RemainderHome.class));
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,UnderDevelopment.class));
            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,UnderDevelopment.class));
            }
        });
        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,TodoNew.class));
            }
        });
        bussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,UnderDevelopment.class));
            }
        });
        sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,StickyNotes.class));
            }
        });
        visiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,UnderDevelopment.class));
            }
        });
        stdcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,UnderDevelopment.class));
            }
        });
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,GoalSetting.class));
            }
        });
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButtonPage1.this,PurchaseList.class));
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    protected void onStart() {
//        super.onStart();
//        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
//        if(opr.isDone()){
//            GoogleSignInResult result=opr.get();
//            handleSignInResult(result);
//        }else{
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
//    }
//
//    private void handleSignInResult(GoogleSignInResult result){
//        if(result.isSuccess()){
//            GoogleSignInAccount account=result.getSignInAccount();
//            userName.setText(account.getDisplayName());
//            userEmail.setText(account.getEmail());
//            //userId.setText(account.getId());
//            try{
//                Glide.with(this).load(account.getPhotoUrl()).into(userPic);
//            }catch (NullPointerException e){
//                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
//            }
//
//        }else{
//            gotoMainActivity();
//        }
//    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void gotoMainActivity(){

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public void authenticateApp() {
//        //Get the instance of KeyGuardManager
//
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
//
//        //Check if the device version is greater than or equal to Lollipop(21)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //Create an intent to open device screen lock screen to authenticate
//            //Pass the Screen Lock screen Title and Description
//            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock), getResources().getString(R.string.confirm_pattern));
//            try {
//                //Start activity for result
//                startActivityForResult(i, LOCK_REQUEST_CODE);
//            } catch (Exception e) {
//
//                //If some exception occurs means Screen lock is not set up please set screen lock
//                //Open Security screen directly to enable patter lock
//                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
//                try {
//
//                    //Start activity for result
//                    Toast.makeText(this, "SetUp Screen Lock", Toast.LENGTH_SHORT).show();
//                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
//                } catch (Exception ex) {
//                    Toast.makeText(this, getResources().getString(R.string.setting_label), Toast.LENGTH_SHORT).show();
//                    //If app is unable to find any Security settings then user has to set screen lock manually
//                    //textView.setText(getResources().getString(R.string.setting_label));
//                }
//            }
//        }
//    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case LOCK_REQUEST_CODE:
//                if (resultCode == RESULT_OK) {
//                    //If screen lock authentication is success update text
//                    Toast.makeText(this, getResources().getString(R.string.unlock_success), Toast.LENGTH_SHORT).show();
//                    //textView.setText(getResources().getString(R.string.unlock_success));
//                } else {
//                    //If screen lock authentication is failed update text
//                    Toast.makeText(this, getResources().getString(R.string.unlock_failed), Toast.LENGTH_SHORT).show();
//                    //finish();
//                    //System.exit(3);
//                    Toast.makeText(this, getResources().getString(R.string.unlock_failed), Toast.LENGTH_SHORT).show();
//                    //textView.setText(getResources().getString(R.string.unlock_failed));
//                }
//                break;
//            case SECURITY_SETTING_REQUEST_CODE:
//                //When user is enabled Security settings then we don't get any kind of RESULT_OK
//                //So we need to check whether device has enabled screen lock or not
//                if (isDeviceSecure()) {
//                    //If screen lock enabled show toast and start intent to authenticate user
//                    Toast.makeText(this, getResources().getString(R.string.device_is_secure), Toast.LENGTH_SHORT).show();
//                    authenticateApp();
//                } else {
//                    //If screen lock is not enabled just update text
//                    Toast.makeText(this, getResources().getString(R.string.security_device_cancelled), Toast.LENGTH_SHORT).show();
//                    //textView.setText(getResources().getString(R.string.security_device_cancelled));
//                }
//
//                break;
//        }
//    }
//
//    /**
//     * method to return whether device has screen lock enabled or not
//     **/
//    private boolean isDeviceSecure() {
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
//
//        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();
//
//        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23
//
//    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            Toast.makeText(this, "ddddddd", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }
    //////////////////////////////////////////////////////////////////////////////////////////////
}