package com.epam.cleandesign.dip.thirdpartyjar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author josesaidolanogarcia
 */
public class EntityManager implements EntityManagerI {

    @Override
    public List<NewsArticleTable> getAll() {
        List<NewsArticleTable> queryResult = new ArrayList<>();
        IntStream.rangeClosed(1, 3)
                .forEach(value -> {
                    NewsArticleTable article = new NewsArticleTable();
                    article.setId((long) value);
                    article.setHeadline("Headline " + value);
                    article.setDescription("News description " + value);
                    article.setNewsType("Regional");
                    queryResult.add(article);
                });
        IntStream.rangeClosed(4, 10)
                .forEach(value -> {
                    NewsArticleTable article = new NewsArticleTable();
                    article.setId((long) value);
                    article.setHeadline("Headline " + value);
                    article.setDescription("News description " + value);
                    article.setNewsType("National");
                    queryResult.add(article);
                });
        return queryResult;
    }

}
