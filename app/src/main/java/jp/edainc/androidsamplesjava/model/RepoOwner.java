package jp.edainc.androidsamplesjava.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * リポジトリ所有者
 */

public class RepoOwner implements Serializable {

    public String login;

    public int id;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("url")
    public String infoUrl;
}
