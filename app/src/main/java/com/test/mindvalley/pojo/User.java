
package com.test.mindvalley.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("profile_image")
    @Expose
    public ProfileImage profileImage;
    @SerializedName("links")
    @Expose
    public Links links;

}
