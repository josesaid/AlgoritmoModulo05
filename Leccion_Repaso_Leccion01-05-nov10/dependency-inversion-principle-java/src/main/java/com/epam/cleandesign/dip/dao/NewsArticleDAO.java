package com.epam.cleandesign.dip.dao;

import com.epam.cleandesign.dip.thirdpartyjar.EntityManager;
import com.epam.cleandesign.dip.thirdpartyjar.EntityManagerI;
import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author josesaidolanogarcia
 */
public class NewsArticleDAO implements NewsArticleDaoI{

    private EntityManagerI entityManagerI;

    public NewsArticleDAO(EntityManagerI entityManagerI) {
        this.entityManagerI = entityManagerI;
    }

    @Override
    public List<NewsArticleTable> findByNewsType(String newsType) {
        return entityManagerI.getAll()
                .stream()
                .filter(article -> newsType.equals(article.getNewsType()))
                .collect(Collectors.toList());
    }

}
