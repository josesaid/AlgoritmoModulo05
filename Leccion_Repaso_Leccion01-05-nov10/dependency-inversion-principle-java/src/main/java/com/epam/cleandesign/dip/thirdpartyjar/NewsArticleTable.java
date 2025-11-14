package com.epam.cleandesign.dip.thirdpartyjar;

/**
 * @author josesaidolanogarcia
 */
public class NewsArticleTable {

    private Long id;
    private String headline;
    private String description;
    private String newsType;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}