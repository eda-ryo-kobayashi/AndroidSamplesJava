package jp.edainc.androidsamplesjava.data.network;

import java.util.List;

import io.reactivex.Single;
import jp.edainc.androidsamplesjava.model.Repository;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * Githubのリポジトリ一覧取得API
 */

public interface GetRepositoriesApi {

    String TAG = GetRepositoriesApi.class.getSimpleName();

    @GET(ApiPath.GET_REPOSITORIES)
    Single<Response< List<Repository> >> getRepositories(@Query("since") String since);
}
