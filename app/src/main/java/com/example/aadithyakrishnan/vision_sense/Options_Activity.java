package com.example.aadithyakrishnan.vision_sense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options_Activity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_options_);

    }
   public void option1(View v)
    {
        Intent i1 =new Intent(this,Evaluate_Activity_1.class);
        startActivity(i1);
    }
   public void option2(View v)
    {
            Intent i2=new Intent(this,MainActivity.class);
            startActivity(i2);
    }
}
