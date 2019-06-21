package com.example.didalife;

public class News {
    private String newsTitle;
    private String newsUrl;
    private String newsTime;

    public News(String newsTitle, String newsUrl,  String newsTime) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTime = newsTime;
    }



    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}