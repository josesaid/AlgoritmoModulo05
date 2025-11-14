package com.epam.cleandesign.dip;

import com.epam.cleandesign.dip.api.NewsBroadcaster;
import com.epam.cleandesign.dip.dao.NewsArticleDAO;
import com.epam.cleandesign.dip.publishing.PublishService;
import com.epam.cleandesign.dip.thirdpartyjar.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsBroadcasterTest {

    private NewsBroadcaster newsBroadcaster;

    @BeforeEach
    public void setUp() {
        NewsArticleDAO newsArticleDAO = new NewsArticleDAO(new EntityManager());
        PublishService publishService = new PublishService();
        newsBroadcaster = new NewsBroadcaster(newsArticleDAO, publishService);
        System.out.println("Setup method");
    }

    @Test
    public void shouldPublishRegionalNews() {
        String regional = newsBroadcaster.broadcastNews("Regional");
        assertTrue(regional.startsWith("Regional News:"));
        assertTrue(regional.contains("<"));
        assertTrue(regional.contains(">"));
        System.out.println("shouldPublishRegionalNews");
    }

    @Test
    public void shouldPublishNationalNews() {
        String national = newsBroadcaster.broadcastNews("National");
        assertTrue(national.startsWith("National News:"));
        assertFalse(national.contains("<"));
        assertFalse(national.contains(">"));
        assertTrue(national.contains(" -- "));
        System.out.println("shouldPublishNationalNews");
    }
}