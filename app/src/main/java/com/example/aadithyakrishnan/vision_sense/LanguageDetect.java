package com.example.aadithyakrishnan.vision_sense;


import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;

/**
 * Created by Akarsh T S on 11-07-2017.
 */
public class LanguageDetect{
    Language source;
    public Language detect(String string){
        switch (string){
            case "af":source= Language.AFRIKAANS;break;
            case "ar": source=Language.ARABIC;break;
            case "az": source=Language.AZERBAIJANI;break;
            case "ba": source=Language.BASHKIR;break;
            case "be": source=Language.BELARUSIAN;break;
            case "bg": source=Language.BULGARIAN;break;
            case "bn": source=Language.BENGALI;break;
            case "bs": source=Language.BOSNIAN;break;
            case "cs": source=Language.CZECH;break;
            case "cv": source=Language.CHUVASH;break;
            case "da": source=Language.DANISH;break;
            case "de": source=Language.GERMAN;break;
            case "el": source=Language.GREEK;break;
            case "en": source=Language.ENGLISH;break;
            case "eo": source=Language.ESPERANTO;break;
            case "es": source=Language.SPANISH;break;
            case "et": source=Language.ESTONIAN;break;
            case "eu": source=Language.BASQUE;break;
            case "fa": source=Language.PERSIAN;break;
            case "fi": source=Language.FINNISH;break;
            case "fr": source=Language.FRENCH;break;
            case "gu": source=Language.GUJARATI;break;
            case "he": source=Language.HEBREW;break;
            case "hi": source=Language.HINDI;break;
            case "ht": source=Language.HAITIAN;break;
            case "hu": source=Language.HUNGARIAN;break;
            case "hy": source=Language.ARMENIAN;break;
            case "id": source=Language.INDONESIAN;break;
            case "is": source=Language.ICELANDIC;break;
            case "it": source=Language.ITALIAN;break;
            case "ja": source=Language.JAPANESE;break;
            case "ka": source=Language.GEORGIAN;break;
            case "kk": source=Language.KAZAKH;break;
            case "km": source=Language.CENTRAL_KHMER;break;
            case "ko": source=Language.KOREAN;break;
            case "ku": source=Language.KURDISH;break;
            case "ky": source=Language.KIRGHIZ;break;
            case "lt": source=Language.LITHUANIAN;break;
            case "lv": source=Language.LATVIAN;break;
            case "ml": source=Language.MALAYALAM;break;
            case "mn": source=Language.MONGOLIAN;break;
            case "nb": source=Language.NORWEGIAN_BOKMAL;break;
            case "nl": source=Language.DUTCH;break;
            case "nn": source=Language.NORWEGIAN_NYNORSK;break;
            case "pa": source=Language.PANJABI;break;
            case "pl": source=Language.POLISH;break;
            case "ps": source=Language.PUSHTO;break;
            case "pt": source=Language.PORTUGUESE;break;
            case "ro": source=Language.ROMANIAN;break;
            case "ru": source=Language.RUSSIAN;break;
            case "sk": source=Language.SLOVAKIAN;break;
            case "so": source=Language.SOMALI;break;
            case "sq": source=Language.ALBANIAN;break;
            case "sv": source=Language.SWEDISH;break;
            case "ta": source=Language.TAMIL;break;
            case "te": source=Language.TELUGU;break;
            case "tr": source=Language.TURKISH;break;
            case "uk": source=Language.UKRAINIAN;break;
            case "ur": source=Language.URDU;break;
            case "vi": source=Language.VIETNAMESE;break;
            case "zh": source=Language.CHINESE;break;
            case "zh-TW": source=Language.TRADITIONAL_CHINESE;break;
        }
        return source;
    }
}

