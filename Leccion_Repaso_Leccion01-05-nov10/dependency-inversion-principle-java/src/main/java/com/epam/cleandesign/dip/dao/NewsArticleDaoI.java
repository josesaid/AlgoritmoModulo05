package com.epam.cleandesign.dip.dao;

import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;

/**
 * @author josesaidolanogarcia
 */
public interface NewsArticleDaoI {
    List<NewsArticleTable> findByNewsType(String newsType);
}
