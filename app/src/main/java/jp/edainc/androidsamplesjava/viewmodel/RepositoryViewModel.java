package jp.edainc.androidsamplesjava.viewmodel;

import com.uber.autodispose.SingleScoper;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import jp.edainc.androidsamplesjava.AppContext;
import jp.edainc.androidsamplesjava.lifecycle.SingleScopeProvider;
import jp.edainc.androidsamplesjava.model.Repository;
import jp.edainc.androidsamplesjava.repository.GithubRepository;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * GithubリポジトリのViewModel
 */

public class RepositoryViewModel {

    private SingleScopeProvider provider;
    private GithubRepository githubRepository;

    public RepositoryViewModel(AppContext app, SingleScopeProvider provider) {
        this.provider = provider;
        githubRepository = new GithubRepository(app.dataAccessComponent());
    }

    public Disposable getRepositories(Consumer<List<Repository>> success,
                                      String since) {
        return githubRepository.getRepositories(since)
            .to(new SingleScoper<>(provider))
            .subscribe(success);
    }
}
