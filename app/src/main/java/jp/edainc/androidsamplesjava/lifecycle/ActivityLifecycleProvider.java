package jp.edainc.androidsamplesjava.lifecycle;

/**
 * Created by kobayashiryou on 2017/09/15.
 *
 * Activity用のLifecycleProvider
 */

public class ActivityLifecycleProvider {

    private SingleScopeProvider lifecycleOnCreate = new SingleScopeProvider();
    private SingleScopeProvider lifecycleOnStart = new SingleScopeProvider();
    private SingleScopeProvider lifecycleOnResume = new SingleScopeProvider();

    public SingleScopeProvider onCreate() {
        return lifecycleOnCreate;
    }


    public SingleScopeProvider onStart() {
        return lifecycleOnStart;
    }

    public SingleScopeProvider onResume() {
        return lifecycleOnResume;
    }
}
