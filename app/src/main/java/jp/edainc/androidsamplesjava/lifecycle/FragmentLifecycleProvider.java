package jp.edainc.androidsamplesjava.lifecycle;

/**
 * Created by kobayashiryou on 2017/09/15.
 *
 * Fragment用のLifecycleProvider
 */

public class FragmentLifecycleProvider {

    private SingleScopeProvider lifecycleOnCreate = new SingleScopeProvider();
    private SingleScopeProvider lifecycleOnActivityCreated = new SingleScopeProvider();
    private SingleScopeProvider lifecycleOnStart = new SingleScopeProvider();
    private SingleScopeProvider lifecycleOnResume = new SingleScopeProvider();

    public SingleScopeProvider onCreate() {
        return lifecycleOnCreate;
    }

    public SingleScopeProvider onActivityCreated() {
        return lifecycleOnActivityCreated;
    }

    public SingleScopeProvider onStart() {
        return lifecycleOnStart;
    }

    public SingleScopeProvider onResume() {
        return lifecycleOnResume;
    }
}
