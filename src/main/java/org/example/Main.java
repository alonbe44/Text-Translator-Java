package org.example;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    // variable to store the best translation
    private static String bestTranslation = "";

    // variable to store the highest match value
    private static double highestMatch = -9999;


    public static void main(String[] args) {

        // variable to store the source language
        String sourceLanguage;
        // Set the default target language
        String targetLanguage;
        // Scanner to take user input
        Scanner inputScanner = new Scanner(System.in);
        while (true){
        // Print welcome message and list of supported languages
        System.out.println("Welcome To Text Translator:");
        System.out.println("Input -1 to exit");
        System.out.println("Please Input The Language you want to translate From-To In Form of xx:");
        System.out.print("From:");

//        Supported Languages :
//                "af-ZA >Afrikaans" +
//                "sq-AL >Albanian" +
//                "am-ET >Amharic" +
//                "ar-SA >Arabic" +
//                "hy-AM >Armenian" +
//                "az-AZ >Azerbaijani" +
//                "bjs-BB >Bajan" +
//                "rm-RO >Balkan Gipsy" +
//                "eu-ES >Basque" +
//                "bem-ZM >Bemba" +
//                "bn-IN >Bengali" +
//                "be-BY >Bielarus" +
//                "bi-VU >Bislama" +
//                "bs-BA >Bosnian" +
//                "br-FR >Breton" +
//                "bg-BG >Bulgarian" +
//                "my-MM >Burmese" +
//                "ca-ES >Catalan" +
//                "cb-PH >Cebuano" +
//                "ch-GU >Chamorro" +
//                "zh-CN >Chinese (Simplified)" +
//                "zh-TW >Chinese Traditional" +
//                "zdj-KM >Comorian (Ngazidja)" +
//                "cop-EG >Coptic" +
//                "aig-AG >Creole English (Antigua and Barbuda)" +
//                "bah-BS >Creole English (Bahamas)" +
//                "gcl-GD >Creole English (Grenadian)" +
//                "gyn-GY >Creole English (Guyanese)" +
//                "jam-JM >Creole English (Jamaican)" +
//                "svc-VC >Creole English (Vincentian)" +
//                "vic-US >Creole English (Virgin Islands)" +
//                "ht-HT >Creole French (Haitian)" +
//                "acf-LC >Creole French (Saint Lucian)" +
//                "crs-SC >Creole French (Seselwa)" +
//                "pov-GW >Creole Portuguese (Upper Guinea)" +
//                "hr-HR >Croatian" +
//                "cs-CZ >Czech" +
//                "da-DK >Danish" +
//                "nl-NL >Dutch" +
//                "dz-BT >Dzongkha" +
//                "en-GB >English" +
//                "eo-EU >Esperanto" +
//                "et-EE >Estonian" +
//                "fn-FNG >Fanagalo" +
//                "fo-FO >Faroese" +
//                "fi-FI >Finnish" +
//                "fr-FR >French" +
//                "gl-ES >Galician" +
//                "ka-GE >Georgian" +
//                "de-DE >German" +
//                "el-GR >Greek" +
//                "grc-GR >Greek (Classical)" +
//                "gu-IN >Gujarati" +
//                "ha-NE >Hausa" +
//                "haw-US >Hawaiian" +
//                "he-IL >Hebrew" +
//                "hi-IN >Hindi" +
//                "hu-HU >Hungarian" +
//                "is-IS >Icelandic" +
//                "id-ID >Indonesian" +
//                "kl-GL >Inuktitut (Greenlandic)" +
//                "ga-IE >Irish Gaelic \n" +
//                "it-IT >Italian" +
//                "ja-JP >Japanese" +
//                "jv-ID >Javanese" +
//                "kea-CV >Kabuverdianu" +
//                "kab-DZ >Kabylian" +
//                "kn-IN >Kannada" +
//                "kk-KZ >Kazakh" +
//                "km-KM >Khmer" +
//                "rw-RW >Kinyarwanda" +
//                "rn-BI >Kirundi" +
//                "ko-KR >Korean" +
//                "ku-TR >Kurdish" +
//                "ckb-IQ >Kurdish Sorani" +
//                "ky-KG >Kyrgyz" +
//                "lo-LA >Lao" +
//                "la-VA >Latin" +
//                "lv-LV >Latvian" +
//                "lt-LT >Lithuanian" +
//                "lb-LU >Luxembourgish" +
//                "mk-MK >Macedonian" +
//                "mg-MG >Malagasy" +
//                "ms-MY >Malay" +
//                "dv-MV >Maldivian" +
//                "mt-MT >Maltese" +
//                "gv-IM >Manx Gaelic" +
//                "mi-NZ >Maori" +
//                "mh-MH >Marshallese" +
//                "men-SL >Mende" +
//                "mn-MN >Mongolian" +
//                "mfe-MU >Morisyen" +
//                "ne-NP >Nepali" +
//                "niu-NU >Niuean" +
//                "no-NO >Norwegian" +
//                "ny-MW >Nyanja" +
//                "ur-PK >Pakistani" +
//                "pau-PW >Palauan" +
//                "pa-IN >Panjabi" +
//                "pap-CW >Papiamentu" +
//                "ps-PK >Pashto" +
//                "fa-IR >Persian \n" +
//                "pis-SB >Pijin" +
//                "pl-PL >Polish" +
//                "pt-PT >Portuguese" +
//                "pot-US >Potawatomi" +
//                "qu-PE >Quechua" +
//                "ro-RO >Romanian" +
//                "ru-RU >Russian" +
//                "sm-WS >Samoan" +
//                "sg-CF >Sango" +
//                "gd-GB >Scots Gaelic" +
//                "sr-RS >Serbian" +
//                "sn-ZW >Shona" +
//                "si-LK >Sinhala" +
//                "sk-SK >Slovak" +
//                "sl-SI >Slovenian" +
//                "so-SO >Somali" +
//                "st-ST >Sotho, Southern" +
//                "es-ES >Spanish" +
//                "srn-SR >Sranan Tongo" +
//                "sw-SZ >Swahili" +
//                "sv-SE >Swedish \n" +
//                "de-CH >Swiss German" +
//                "syc-TR >Syriac (Aramaic)" +
//                "tl-PH >Tagalog" +
//                "tg-TJ >Tajik" +
//                "tmh-DZ >Tamashek (Tuareg)" +
//                "ta-LK >Tamil" +
//                "te-IN >Telugu" +
//                "tet-TL >Tetum" +
//                "th-TH >Thai" +
//                "bo-CN >Tibetan" +
//                "ti-TI >Tigrinya" +
//                "tpi-PG >Tok Pisin" +
//                "tkl-TK >Tokelauan" +
//                "to-TO >Tongan" +
//                "tn-BW >Tswana" +
//                "tr-TR >Turkish" +
//                "tk-TM >Turkmen" +
//                "tvl-TV >Tuvaluan" +
//                "uk-UA >Ukrainian" +
//                "ppk-ID >Uma" +
//                "uz-UZ >Uzbek" +
//                "vi-VN >Vietnamese" +
//                "wls-WF >Wallisian" +
//                "cy-GB >Welsh" +
//                "wo-SN >Wolof" +
//                "xh-ZA >Xhosa" +
//                "yi-YD >Yiddish" +
//                "zu-ZA >Zulu "


        sourceLanguage = inputScanner.nextLine();
        System.out.print("To:");
        targetLanguage = inputScanner.nextLine();
        System.out.println("Please Input the Text You Want to translate:");
        String textToTranslate = inputScanner.nextLine();

        //Check if the user has entered "-1" as the source language, target language, or text to translate
        // If any of these values are "-1", break out of the loop to end the program.
        if(sourceLanguage.equals("-1")||targetLanguage.equals("-1")||textToTranslate.equals("-1")) {
        break;
        }

        try {
            // Create an HTTP client
            OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
            // Build the HTTP request
            Request request = new Request.Builder()
                    .url("https://api.mymemory.translated.net/get?q=" + textToTranslate + "&langpair=" + sourceLanguage + "|" + targetLanguage)
                    .get()
                    .build();

            // Send the HTTP request and get the response
            Response response = httpClient.newCall(request).execute();
            // Get the bytes of the response
            assert response.body() != null;
            byte[] responseBytes = response.body().bytes();
            // Convert the response bytes to a string
            String responseString = new String(responseBytes, StandardCharsets.UTF_8);
            // Create a JSON object from the response string
            JSONObject jsonResponse = new JSONObject(responseString);
            // Get the matches array from the JSON object
            JSONArray matches = jsonResponse.getJSONArray("matches");

            // Loop through all the matches
            for (int i = 0; i < matches.length(); i++) {
                // Get the current match
                JSONObject currentMatch = matches.getJSONObject(i);
                // Check if the match is higher than the highest match so far
                if (currentMatch.getDouble("match") >= highestMatch) {
                    // Update the best translation and highest match
                    bestTranslation = currentMatch.getString("translation");
                    highestMatch = currentMatch.getDouble("match");
                }
            }
            // Print the best translation
            System.out.println("Translated Text :"+bestTranslation+"\n");
        }

        catch (IOException e) {
            // Print the error message
            System.out.println(e.getMessage());
        }

        catch (Exception e){
            // Check if the error message is due to unsupported language or wrong input
            if(e.getMessage().equals("JSONObject[\"matches\"] is not a JSONArray.")){
                System.out.println("Sorry  Language is not supported or wrong Input");
            }
            else {
                System.out.println(e.getMessage());
            }
        }
       }
        System.out.println("Thank You For Using Translator");

    }
}