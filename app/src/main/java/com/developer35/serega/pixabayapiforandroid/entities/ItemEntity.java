package com.developer35.serega.pixabayapiforandroid.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemEntity {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("pageURL")
    @Expose
    private String pageURL;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("tags")
    @Expose
    private String tags;

    @SerializedName("previewURL")
    @Expose
    private String previewURL;

    @SerializedName("previewWidth")
    @Expose
    private Integer previewWidth;

    @SerializedName("previewHeight")
    @Expose
    private Integer previewHeight;

    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;

    @SerializedName("webformatWidth")
    @Expose
    private Integer webformatWidth;

    @SerializedName("webformatHeight")
    @Expose
    private Integer webformatHeight;

    @SerializedName("imageWidth")
    @Expose
    private Integer imageWidth;

    @SerializedName("imageHeight")
    @Expose
    private Integer imageHeight;

    @SerializedName("imageSize")
    @Expose
    private Long imageSize;

    @SerializedName("views")
    @Expose
    private Long views;

    @SerializedName("downloads")
    @Expose
    private Long downloads;

    @SerializedName("favorites")
    @Expose
    private String favorites;

    @SerializedName("likes")
    @Expose
    private String likes;

    @SerializedName("comments")
    @Expose
    private Long comments;

    @SerializedName("user_id")
    @Expose
    private Long userId;

    @SerializedName("user")
    @Expose
    private String userName;

    @SerializedName("userImageURL")
    @Expose
    private String userImageURL;

    public Long getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public Integer getPreviewWidth() {
        return previewWidth;
    }

    public Integer getPreviewHeight() {
        return previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public Integer getWebformatWidth() {
        return webformatWidth;
    }

    public Integer getWebformatHeight() {
        return webformatHeight;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public Long getViews() {
        return views;
    }

    public Long getDownloads() {
        return downloads;
    }

    public String getFavorites() {
        return favorites;
    }

    public String getLikes() {
        return likes;
    }

    public Long getComments() {
        return comments;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", pageURL='" + pageURL + '\'' +
                ", type='" + type + '\'' +
                ", tags='" + tags + '\'' +
                ", previewURL='" + previewURL + '\'' +
                ", previewWidth=" + previewWidth +
                ", previewHeight=" + previewHeight +
                ", webformatURL='" + webformatURL + '\'' +
                ", webformatWidth=" + webformatWidth +
                ", webformatHeight=" + webformatHeight +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", imageSize=" + imageSize +
                ", views=" + views +
                ", downloads=" + downloads +
                ", favorites=" + favorites +
                ", likes=" + likes +
                ", comments=" + comments +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userImageURL='" + userImageURL + '\'' +
                '}';
    }
}
