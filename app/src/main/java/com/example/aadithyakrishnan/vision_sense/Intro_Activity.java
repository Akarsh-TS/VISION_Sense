package com.example.aadithyakrishnan.vision_sense;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Intro_Activity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=100;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent intent=new Intent(Intro_Activity.this,Options_Activity.class);
                       startActivity(intent);


                   }
               },1000);




    }
}
