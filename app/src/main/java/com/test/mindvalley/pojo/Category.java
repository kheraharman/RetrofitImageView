
package com.test.mindvalley.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("photo_count")
    @Expose
    public Integer photoCount;
    @SerializedName("links")
    @Expose
    public Links_ links;

}
