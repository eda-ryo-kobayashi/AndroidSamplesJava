package jp.edainc.androidsamplesjava.data.network;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import jp.edainc.androidsamplesjava.model.RepoOwner;
import jp.edainc.androidsamplesjava.model.Repository;
import jp.edainc.androidsamplesjava.utility.ThreadUtility;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by kobayashiryou on 2017/10/26.
 */

final class GetRepositoriesApiMock implements GetRepositoriesApi {
    @Override
    public Single<Response<List<Repository>>> getRepositories(@Query("since") String since) {
        return Single.create(e -> {
            ThreadUtility.sleep(500);

            List<Repository> res = new ArrayList<>();

            for(int i = 0; i < 10; i++) {
                Repository r = new Repository();
                int idx = i+1;
                r.id = idx;
                r.name = "name:"+idx;
                r.fullName = "full name:"+idx;
                r.description = "description:"+idx;
                r.url = "https://google.com";
                r.owner = new RepoOwner();
                r.owner.id = idx;
                r.owner.login = "login"+idx;
                r.owner.avatarUrl = "https://karakuri.link/wp-content/uploads/2015/11/04.jpg";
                r.owner.infoUrl = "https://google.com";
                res.add(r);
            }

            Response<List<Repository>> result = Response.success(res);
            e.onSuccess(result);
        });
    }
}
