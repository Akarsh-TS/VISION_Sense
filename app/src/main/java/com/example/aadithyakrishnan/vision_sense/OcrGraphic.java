/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.aadithyakrishnan.vision_sense;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.StrictMode;
import android.util.Log;

import com.example.aadithyakrishnan.vision_sense.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class OcrGraphic extends GraphicOverlay.Graphic {

    private int mId;

    private static final int TEXT_COLOR = Color.WHITE;
    LanguageDetect languageDetect = new LanguageDetect();
    private static Paint sRectPaint;
    private static Paint sTextPaint;
    private final TextBlock mText;
    public static String image, result, auto;
    spinnerselect spinnerselect = new spinnerselect();
    Language source,target;
    OcrGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);
        mText = text;
        image = mText.getValue();

        if (sRectPaint == null) {
            sRectPaint = new Paint();
            sRectPaint.setColor(TEXT_COLOR);
            sRectPaint.setStyle(Paint.Style.STROKE);
            sRectPaint.setStrokeWidth(4.0f);
        }

        if (sTextPaint == null) {
            sTextPaint = new Paint();
            sTextPaint.setColor(TEXT_COLOR);
            sTextPaint.setTextSize(54.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public TextBlock getTextBlock() {
        return mText;
    }

    /**
     * Checks whether a point is within the bounding box of this graphic.
     * The provided point should be relative to this graphic's containing overlay.
     *
     * @param x An x parameter in the relative context of the canvas.
     * @param y A y parameter in the relative context of the canvas.
     * @return True if the provided point is contained within this graphic's bounding box.
     */
    public boolean contains(float x, float y) {
        TextBlock text = mText;
        if (text == null) {
            return false;
        }
        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        return (rect.left < x && rect.right > x && rect.top < y && rect.bottom > y);
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        TextBlock text = mText;
        if (text == null) {
            return;
        }
        //LanguageTranslator service = new LanguageTranslator();
        //service.setUsernameAndPassword("c7cd1bcb-351f-41da-8937-5036f8b5f0ed", "uhGPPppGUzVj");
        // Draws the bounding box around the TextBlock.
        RectF rect = new RectF(mText.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, sRectPaint);

        Log.v("result", text.getValue().toString());
        // Break the text into multiple lines and draw each one according to its own bounding box.
        List<? extends Text> textComponents = text.getComponents();
        for (Text currentText : textComponents) {
            //Log.v("text",currentText.toString());
            //service.translate(currentText.toString(), Language.ENGLISH,Language.HINDI).execute().toString();
            float left = translateX(currentText.getBoundingBox().left);
            float bottom = translateY(currentText.getBoundingBox().bottom);
            //translate("hello");
            if (OcrCaptureActivity.translation == false) {
                canvas.drawText(currentText.getValue(), left, bottom, sTextPaint);
                //}
            } else {
                canvas.drawText(translate(currentText.getValue()), left, bottom, sTextPaint);

            }
        }
    }

    /*  private class WatsonTask extends AsyncTask<String , Void, String> {
          @Override
          protected String doInBackground(String... text1) {
              Log.v("result",text1[0]);
              Log.v("TASk","inasynk");
              TranslationResult translationResult = service.translate(
                      text1[0], Language.ENGLISH, Language.FRENCH)
                      .execute();

              System.out.println(translationResult);
              Log.v("resultafter",translationResult.toString());
              try {
                  JSONObject object = new JSONObject(translationResult.toString());
                  JSONArray array = object.getJSONArray("translations");
                  JSONObject object1 = array.getJSONObject(0);
                  result = object1.getString("translations");

              } catch (JSONException e) {
                  Log.v("ERROR", "NO OBJECt");
              }
              return translationResult.toString();
          }

          @Override
          protected void onPostExecute(String result1) {
              result=result1;    }

      }*/
    public String translate(String text) {
        //String result = "";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        LanguageTranslator service = new LanguageTranslator();
        service.setUsernameAndPassword("c7cd1bcb-351f-41da-8937-5036f8b5f0ed", "uhGPPppGUzVj");
        //String s=service.identify(text).execute().toString();
        //Log.v("language",s);
        List<IdentifiedLanguage> langs = service.identify(text).execute();
        // System.out.println(langs);

        Log.v("langs", langs.toString());
        Gson gson=new Gson();
        try {


            JSONArray arra = new JSONArray(gson.toJson(langs));

            JSONObject object=arra.getJSONObject(0);
             auto=object.getString("language");
            Log.v("JSONARRAY", auto);
        }
        catch (JSONException j){}
        source=spinnerselect.source(MainActivity.source);
        target=spinnerselect.target(MainActivity.target);
        Log.v("detects",source.toString());
        Log.v("detectt",target.toString());
        TranslationResult translationResult = service.translate(
                text,source , target)
                .execute();
        /*TranslationResult translationResult = service.translate(
                text, Language.ENGLISH, Language.FRENCH)
                .execute();*/

        //System.out.println(translationResult);
        Log.v("INSIDE TRAnslation", translationResult.toString());
        try {
            JSONObject object = new JSONObject(translationResult.toString());
            JSONArray array = object.getJSONArray("translations");
            result = array.getJSONObject(0).getString("translation");
            //JSONObject object1 = array.getJSONObject(0);
            //result = object1.getString("translations");
            //result=array.toString();
        } catch (JSONException ea) {
            Log.v("ERROR", "NO OBJECt");
        }
   /* Log.v("translation",result);
    WatsonTask task = new WatsonTask();
    task.execute(text);**/
        return result;
    }

}


