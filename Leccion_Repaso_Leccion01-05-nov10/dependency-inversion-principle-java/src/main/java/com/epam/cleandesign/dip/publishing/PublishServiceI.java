package com.epam.cleandesign.dip.publishing;

import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;

/**
 * @author josesaidolanogarcia
 */
public interface PublishServiceI {
    String publishNews(String newsType, List<NewsArticleTable> newsArticles);
}
