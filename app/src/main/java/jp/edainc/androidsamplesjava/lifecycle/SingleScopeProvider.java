package jp.edainc.androidsamplesjava.lifecycle;

import com.uber.autodispose.LifecycleScopeProvider;
import com.uber.autodispose.OutsideLifecycleException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/09/15.
 *
 * 一対一のライフサイクルイベントを検知する
 */

public class SingleScopeProvider implements LifecycleScopeProvider<LifecycleEvent> {

    private BehaviorSubject<LifecycleEvent> lifecycle = BehaviorSubject.create();
    private final LifecycleEvent startEvent;
    private LifecycleEvent current;

    public SingleScopeProvider() {
        this.startEvent = LifecycleEvent.CREATE;
        current = startEvent;
    }

    /**
     * ライフサイクルリセット
     */
    public void reset() {
        current = startEvent;
        lifecycle.onNext(current);
    }

    /**
     * ライフサイクルイベント通知
     */
    public void onChangeLifecycle() {
        current = LifecycleEvent.corresponding(startEvent);
        lifecycle.onNext(current);
    }

    @Override
    public Observable<LifecycleEvent> lifecycle() {
        return lifecycle;
    }

    @Override
    public Function<LifecycleEvent, LifecycleEvent> correspondingEvents() {
        return lastEvent -> {
            LifecycleEvent corresponding = LifecycleEvent.corresponding(lastEvent);
            if(corresponding != null) {
                return corresponding;
            }
            throw new OutsideLifecycleException("outside lifecycle");
        };
    }

    @Override
    public LifecycleEvent peekLifecycle() {
        Timber.d("peekLifecycle : %s", current.toString());
        return current;
    }
}
