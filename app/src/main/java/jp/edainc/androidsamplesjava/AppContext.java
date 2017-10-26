package jp.edainc.androidsamplesjava;

import jp.edainc.androidsamplesjava.data.DataAccessComponent;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * アプリケーションコンテキスト
 */

public interface AppContext {

    /**
     * データアクセスコンポーネント取得
     * @return データアクセスコンポーネント
     */
    DataAccessComponent dataAccessComponent();

}
