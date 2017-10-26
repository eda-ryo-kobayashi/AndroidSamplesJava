package jp.edainc.androidsamplesjava.utility;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * API使うときのUtility
 */

public final class ApiUtility {
    private ApiUtility() {}

    public static <T> Function<Response<T>, T> map(String apiName) {
        return map((Action)null, apiName);
    }

    public static <T> Function<Response<T>, T> map(Action success, String apiName) {
        return t -> {
            if(t == null) {
                throw new Exception("API : " + apiName + " response is null");
            }
            T res = t.body();
            if(!t.isSuccessful() || res == null) {
                throw new Exception("API : " + apiName + " code : " + t.code() + " message : " + t.message());
            }
            if(success != null) {
                success.run();
            }
            return res;
        };
    }

    public static <T> Function<Response<T>, T> map(Consumer<T> success, String apiName) {
        return t -> {
            if(t == null) {
                throw new Exception("API : " + apiName + " response is null");
            }
            T res = t.body();
            if(!t.isSuccessful() || res == null) {
                throw new Exception("API : " + apiName + " code : " + t.code() + "message : " + t.message());
            }
            if(success != null) {
                success.accept(res);
            }
            return res;
        };
    }

}
