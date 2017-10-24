package jp.edainc.androidsamplesjava.lifecycle;

/**
 * Created by kobayashiryou on 2017/09/15.
 *
 * ライフサイクルイベント
 */

enum LifecycleEvent {
    CREATE,
    DESTROY,
    ;

    public static LifecycleEvent corresponding(LifecycleEvent event) {
        switch(event) {
            case CREATE: return DESTROY;
            case DESTROY: return CREATE;
        }
        return null;
    }
}
