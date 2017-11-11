package com.example.aadithyakrishnan.vision_sense;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Display extends AppCompatActivity {


    TextView textView;
    ArrayList<String> a;
    ImageView imageView;
    Uri file;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        textView=(TextView)findViewById(R.id.tv);
        imageView=(ImageView)findViewById(R.id.iv);
        a=getIntent().getStringArrayListExtra("data");
        file=Uri.parse(getIntent().getStringExtra("imageuri"));
        for(String s:a)
        {
            textView.append(s);
            textView.append("\n");
        }
        imageView.setImageURI(file);


    }
}

