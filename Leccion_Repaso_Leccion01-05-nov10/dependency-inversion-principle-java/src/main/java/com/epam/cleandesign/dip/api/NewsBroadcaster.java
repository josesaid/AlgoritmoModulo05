package com.epam.cleandesign.dip.api;

import com.epam.cleandesign.dip.dao.NewsArticleDaoI;
import com.epam.cleandesign.dip.publishing.PublishServiceI;
import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;

/**
 * @author josesaidolanogarcia
 */
public class NewsBroadcaster {

    private NewsArticleDaoI newsArticleDaoI ;
    private PublishServiceI publishServiceI;

    public NewsBroadcaster(NewsArticleDaoI newsArticleDaoI, PublishServiceI publishServiceI) {
        this.newsArticleDaoI = newsArticleDaoI;
        this.publishServiceI = publishServiceI;
    }

    public String broadcastNews(String newsType) {
        return publishServiceI.publishNews(newsType, newsArticleDaoI.findByNewsType(newsType));
    }


}
