package jp.edainc.androidsamplesjava.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * リポジトリ情報
 */

public class Repository implements Serializable {

    public int id;

    public String name;

    @SerializedName("full_name")
    public String fullName;

    public RepoOwner owner;

    @SerializedName("html_url")
    public String url;

    public String description;
}
