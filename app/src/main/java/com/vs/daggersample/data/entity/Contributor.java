package com.vs.daggersample.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Valentyn on 13.04.2018.
 */
@Entity
public class Contributor implements Parcelable {

    @PrimaryKey
    private long id;
    private String login;
    @SerializedName("html_url")
    private String url;
    private int contributions;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;
    private String company;
    private String email;
    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Creator<Contributor> getCREATOR() {
        return CREATOR;
    }

    public Contributor() {
    }

    protected Contributor(Parcel in) {
        id = in.readLong();
        login = in.readString();
        url = in.readString();
        contributions = in.readInt();
        avatarUrl = in.readString();
        name = in.readString();
        company = in.readString();
        email = in.readString();
        location = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(login);
        dest.writeString(url);
        dest.writeInt(contributions);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(email);
        dest.writeString(location);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contributor> CREATOR = new Creator<Contributor>() {
        @Override
        public Contributor createFromParcel(Parcel in) {
            return new Contributor(in);
        }

        @Override
        public Contributor[] newArray(int size) {
            return new Contributor[size];
        }
    };

    @Override
    public String toString() {
        return "Contributor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", url='" + url + '\'' +
                ", contributions=" + contributions +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
