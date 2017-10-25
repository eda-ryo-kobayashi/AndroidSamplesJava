package jp.edainc.androidsamplesjava.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * Jsonを整形して出力
 */

public class JsonPrettyPrintLogger implements HttpLoggingInterceptor.Logger {

    private Pattern jsonPattern = Pattern.compile("^\\{.*\\}$");
    private Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    private JsonParser parser = new JsonParser();

    @Override
    public void log(String message) {
        // jsonをきれいに表示
        try {
            Matcher matcher = jsonPattern.matcher(message);
            if(matcher.find()) {
                String json = matcher.group(0);
                String pretty = prettyGson.toJson(parser.parse(json));
                Timber.tag("OkHttp").d(pretty);
            } else {
                Timber.tag("OkHttp").d(message);
            }
        } catch(JsonSyntaxException | IllegalStateException e) {
            Timber.tag("OkHttp").d(message);
        }
    }
}
