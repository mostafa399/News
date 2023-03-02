
package com.mostafahelal.news.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


@Generated("jsonschema2pojo")
public class HomePageModel {
    @SerializedName("CategoryButton")
    @Expose
    private List<CategoryButton> categoryButton;

    public List<CategoryButton> getCategoryButton() {
        return categoryButton;
    }

    public void setCategoryButton(List<CategoryButton> categoryButton) {
        this.categoryButton = categoryButton;
    }

    @SerializedName("banners")
    @Expose
    private List<Banner> banners;
    @SerializedName("ads")
    @Expose
    private List<Ad> ads;
    @SerializedName("news")
    @Expose
    private List<News> news;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    @Generated("jsonschema2pojo")
    public class Ad {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    @Generated("jsonschema2pojo")
    public class Banner {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    @Generated("jsonschema2pojo")
    public class News {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("post_date")
        @Expose
        private String postDate;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("post_content")
        @Expose
        private String postContent;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("sourceUrl")
        @Expose
        private String sourceUrl;
        @SerializedName("sourcelogo")
        @Expose
        private String sourcelogo;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPostContent() {
            return postContent;
        }

        public void setPostContent(String postContent) {
            this.postContent = postContent;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getSourcelogo() {
            return sourcelogo;
        }

        public void setSourcelogo(String sourcelogo) {
            this.sourcelogo = sourcelogo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
    @Generated("jsonschema2pojo")
    public class CategoryButton {

        @SerializedName("cid")
        @Expose
        private Integer cid;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("taxonomy")
        @Expose
        private Object taxonomy;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getCid() {
            return cid;
        }

        public void setCid(Integer cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTaxonomy() {
            return taxonomy;
        }

        public void setTaxonomy(Object taxonomy) {
            this.taxonomy = taxonomy;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

}
