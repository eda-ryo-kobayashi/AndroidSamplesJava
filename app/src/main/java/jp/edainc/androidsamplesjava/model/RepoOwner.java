package jp.edainc.androidsamplesjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * リポジトリ所有者
 */

public class RepoOwner {

    public String login;

    public int id;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("url")
    public String infoUrl;
}
