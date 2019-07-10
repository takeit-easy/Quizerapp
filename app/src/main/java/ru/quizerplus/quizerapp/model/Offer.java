package ru.quizerplus.quizerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Offer extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("browser")
    @Expose
    private Boolean browser;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBrowser() {
        return browser;
    }

    public void setBrowser(Boolean browser) {
        this.browser = browser;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}