package com.swastiknathgroup.positionXid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {
private TextView displayname;
private TextView email;
private TextView userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //displayname=(TextView)findViewById(R.id.displayname);
        email=(TextView)findViewById(R.id.email);
        userid=(TextView)findViewById(R.id.uid);

        Intent intent=getIntent();

        String Displayname= intent.getStringExtra("displayname");
        displayname.setText(Displayname);
       String Email=intent.getStringExtra("email");
       email.setText(Email);
       String uid=intent.getStringExtra("uid");
       userid.setText(uid);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
