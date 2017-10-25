package jp.edainc.androidsamplesjava.data.network;

import io.reactivex.Single;
import jp.edainc.androidsamplesjava.data.network.response.LoginResponse;
import jp.edainc.androidsamplesjava.utility.ThreadUtility;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * ログインAPI mock
 */

final class LoginApiMock implements LoginApi {
    @Override
    public Single<Response<LoginResponse>> login(@Field("userId") String userId, @Field("password") String password) {
        return Single.create(e -> {
            ThreadUtility.sleep(500);

            LoginResponse res = new LoginResponse();
            res.statusCode = 200;

            Response<LoginResponse> result = Response.success(res);
            e.onSuccess(result);
        });
    }
}
