package jp.edainc.androidsamplesjava.data.network;

import io.reactivex.Single;
import jp.edainc.androidsamplesjava.data.network.response.LoginResponse;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * ログインAPI
 */

public interface LoginApi {

    @FormUrlEncoded
    @POST(ApiPath.LOGIN)
    Single<Response<LoginResponse>> login(@Field("userId") String userId,
                                          @Field("password") String password);
}
