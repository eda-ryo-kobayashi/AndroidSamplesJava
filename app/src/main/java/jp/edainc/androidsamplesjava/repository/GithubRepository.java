package jp.edainc.androidsamplesjava.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jp.edainc.androidsamplesjava.data.DataAccessComponent;
import jp.edainc.androidsamplesjava.data.network.GetRepositoriesApi;
import jp.edainc.androidsamplesjava.model.Repository;
import jp.edainc.androidsamplesjava.utility.ApiUtility;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * Githubリポジトリ
 */

public class GithubRepository {

    @Inject
    GetRepositoriesApi getRepositoriesApi;

    public GithubRepository(DataAccessComponent component) {
        component.inject(this);
    }

    public Single<List<Repository>> getRepositories(String since) {
        return getRepositoriesApi.getRepositories(since)
            .subscribeOn(Schedulers.newThread())
            .map(ApiUtility.map(GetRepositoriesApi.TAG))
            .observeOn(AndroidSchedulers.mainThread());
    }
}
