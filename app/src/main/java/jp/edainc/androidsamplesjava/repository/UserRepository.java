package jp.edainc.androidsamplesjava.repository;

import javax.inject.Inject;

import jp.edainc.androidsamplesjava.data.DataAccessComponent;
import jp.edainc.androidsamplesjava.data.network.LoginApi;
import jp.edainc.androidsamplesjava.data.preference.UserData;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * ユーザーリポジトリ
 */

public class UserRepository {

    @Inject
    LoginApi loginApi;
    @Inject
    UserData userData;

    UserRepository(DataAccessComponent component) {
        component.inject(this);
    }

    // メールアドレス・パスワードでログイン
    // Googleでログイン
    // Twitterでログイン
    // Facebookでログイン
    // LINEでログイン
}
