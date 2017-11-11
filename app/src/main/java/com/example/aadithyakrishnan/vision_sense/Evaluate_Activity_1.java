package com.example.aadithyakrishnan.vision_sense;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Evaluate_Activity_1 extends AppCompatActivity {


    ImageView imageView;
    Button button;
    Bitmap b;
    String mCurrentPhotoPath;
    Uri file;
    Uri data1;
    Uri resultUri;
    EvaluateExpression evaluateExpression;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_1);
        evaluateExpression=new EvaluateExpression();

       // imageView=(ImageView)findViewById(R.id.iv);
        button=(Button)findViewById(R.id.button);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }
    File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //  Log.v("path2222222",mediaStorageDir.getPath() + File.separator +"IMG_"+ timeStamp + ".png");
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpeg");

    }
    public void onclick(View v)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f2=getOutputMediaFile();
        file = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", f2);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            //    Log.v("enter","enter");
            // data1=data.getData();
            CropImage.activity(file)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
     /*   if (requestCode == 5) {
            // get the returned data
            Bundle extras = data.getExtras();
            // get the cropped bitmap
            Bitmap thePic = extras.getParcelable("data");
            ImageView picView = (ImageView) findViewById(R.id.iv);
            picView.setImageBitmap(thePic);
        }*/
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                performAnalaysis();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
      /*  if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Log.v("path1",uri.getPath());
            ArrayList<String> a=new ArrayList<>();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.iv);
                imageView.setImageBitmap(bitmap);
                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                Frame.Builder f =new Frame.Builder();
                f.setBitmap(bitmap);
                Frame f1=f.build();
                SparseArray<TextBlock> items= textRecognizer.detect(f1);

                Log.v("s",Integer.toString(items.size()));
                for (int i = 0; i < items.size(); ++i)
                {
                    TextBlock item = items.valueAt(i);
                    if (item != null && item.getValue() != null) {
                        Log.v("input",item.getValue());
                      //  Double d= evaluateExpression.evaluate(item.getValue());

                       // String s=Double.toString(d);
                         String s=item.getValue();
                        Log.v("value",s);

                        a.add(s);
                    }

                }
                Intent i=new Intent(this,Display.class);
                i.putStringArrayListExtra("data",a);
                startActivity(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    void performAnalaysis()
    {
        ArrayList<String> a=new ArrayList<>();
        Bitmap b=BitmapFactory.decodeFile(resultUri.getPath());
        //  Log.v("ssss","ssss");
        // imageView.setImageBitmap(b);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        Frame.Builder f4 =new Frame.Builder();
        f4.setBitmap(b);
        Frame f1=f4.build();
        SparseArray<TextBlock> items= textRecognizer.detect(f1);

        Log.v("s",Integer.toString(items.size()));
        for (int i = 0; i < items.size(); ++i)
        {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null)
            {

                Log.v("input",item.getValue());


                Double d = evaluateExpression.evaluate(item.getValue());
                String s = Double.toString(d);
                //   String s=item.getValue();
                Log.v("value", s);

                a.add(s);



            }

        }
        Intent i=new Intent(this,Display.class);
        i.putStringArrayListExtra("data",a);
        i.putExtra("imageuri",file.toString());
        startActivity(i);

    }


 /*   void onpick(View v)
    {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }*/



}




































