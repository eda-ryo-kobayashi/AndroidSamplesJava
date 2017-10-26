package jp.edainc.androidsamplesjava.data.network;

import static jp.edainc.androidsamplesjava.data.network.ServerInfo.API_PATH;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * 各APIのPATH
 */

public final class ApiPath {
    private ApiPath() {}

    public static final String LOGIN = API_PATH + "/login";
    static final String GET_REPOSITORIES = "/repositories";
}
