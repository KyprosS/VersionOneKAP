package com.example.kypros.versiononekap;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kypros.versiononekap.Common.Common;

public class HelpFeedbackActivity extends BaseActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_help_feedback, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(6).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------



        ((Button) findViewById(R.id.btnOK)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                progressBar= (ProgressBar)findViewById(R.id.progressBar);

                //Hide keyboard after click----------------------------------------------------------------
                LinearLayout mainLayout;
                mainLayout = (LinearLayout)findViewById(R.id.help_feed);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                //Hide keyboard after click------------------------------------END--------------------------



                //Input validation
                String sub = ((EditText)findViewById(R.id.txtSubject)).getText().toString();
                String mess = ((EditText)findViewById(R.id.txtMessage)).getText().toString();

                if (TextUtils.isEmpty(sub)){
                    Toast.makeText(getApplicationContext(),"Please fill your subject!", Toast.LENGTH_LONG).show();
                    return;
                }else if(TextUtils.isEmpty(mess)){
                    Toast.makeText(getApplicationContext(),"Please fill your message!", Toast.LENGTH_LONG).show();
                    return;
                }

                //Check internet Connectivity
                if (Common.isConnectedToInternet(getBaseContext())) {

                    progressBar.setVisibility(View.VISIBLE);

                    String to = "george_patsias@hotmail.co.uk";

                    Intent mail = new Intent(Intent.ACTION_SEND);
                    mail.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                    mail.putExtra(Intent.EXTRA_SUBJECT, sub);
                    mail.putExtra(Intent.EXTRA_TEXT, mess);
                    mail.setType("message/rfc822");
                    startActivity(Intent.createChooser(mail, "Send email via:"));
                }
                else
                {
                    Toast.makeText(HelpFeedbackActivity.this, "Please check your Internet connection!", Toast.LENGTH_SHORT).show();
                    return;
                }










            }
        });


    }//onCreate END

}
