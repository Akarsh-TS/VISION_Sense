 package com.example.aadithyakrishnan.vision_sense;

import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;

/**
 * Created by Akarsh T S on 20-07-2017.
 */

public class spinnerselect {
    Language source,target;
    LanguageDetect languageDetect;

    public Language source(String string){
        switch (string){
            case "AUTO-DETECT":source=languageDetect.detect(OcrGraphic.auto);break;
            case "ENGLISH":source= Language.ENGLISH;break;
            case "ARABIC":source=Language.ARABIC;break;
            case "FRENCH":source=Language.FRENCH;break;
            case "PORTUGESE":source=Language.PORTUGUESE;break;
            case "SPANISH":source=Language.SPANISH;break;
            case "ITALIAN":source=Language.ITALIAN;break;


        }
        return source;
    }
    public Language target(String string){
        switch (string){
            case "ENGLISH":target= Language.ENGLISH;break;
            case "ARABIC":target=Language.ARABIC;break;
            case "FRENCH":target=Language.FRENCH;break;
            case "PORTUGESE":target=Language.PORTUGUESE;break;
            case "SPANISH":target=Language.SPANISH;break;
            case "ITALIAN":target=Language.ITALIAN;break;


        }
    return target;
    }

}
