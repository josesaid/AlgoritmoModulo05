package com.epam.cleandesign.dip.publishing;

import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author josesaidolanogarcia
 */
public class PublishService implements PublishServiceI {

    @Override
    public String publishNews(String newsType, List<NewsArticleTable> newsArticles) {
        return newsType + " News:\n" +
                newsArticles.stream()
                .map(this::formatArticle)
                .collect(Collectors.joining("\n"));
    }

    private String formatArticle(NewsArticleTable article) {
        return article.getNewsType().equals("Regional") ? "<" + article.getHeadline() + ">" :
                article.getHeadline() + " -- " + article.getDescription();
    }

}
