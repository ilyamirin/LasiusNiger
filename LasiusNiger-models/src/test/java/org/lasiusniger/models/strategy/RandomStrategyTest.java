package org.lasiusniger.models.strategy;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Client;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.Zone;
import org.lasiusniger.models.exceptions.ZoneHasNoBannersException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ruslan
 * Date: 06.10.12
 * Time: 21:43
 */
public class RandomStrategyTest {
    Client client;
    Zone zone = new Zone();
    Set<Banner> banners;
    Request request = new Request();
    Banner banner1 = new Banner();
    Banner banner2 = new Banner();
    Banner banner3 = new Banner();
    RandomStrategy strategy = new RandomStrategy();

    @Before
    public void setUp() throws Exception {
        client = new Client();
        zone.setClient(client);

        banners = new HashSet<Banner>();

        banner1.setUUID("1");
        banner1.setWeight(98);
        banner1.setUser(client);
        banners.add(banner1);

        banner2.setUUID("2");
        banner2.setWeight(99);
        banner2.setUser(client);
        banners.add(banner2);

        banner3.setUUID("3");
        banner3.setWeight(100);
        banner3.setUser(client);
        banners.add(banner3);

        request.setZone(zone);
    }

    public void tearDown() throws Exception {

    }

    @Test(expected = ZoneHasNoBannersException.class)
    public void testEmptyZone() throws ZoneHasNoBannersException {
        strategy.chooseBanner(request);
    }

    @Test
    public void testChooseBanner() throws ZoneHasNoBannersException {
        zone.setBanners(banners);
        Banner banner = strategy.chooseBanner(request);
        Assert.assertTrue(banners.contains(banner));
    }

    /**
     * тест распределения вероятности показов баннеров.
     * при случайно стратегии баннера должны получить одинаковое количство
     * показво с точностью до велечины error
     * @throws ZoneHasNoBannersException
     */
    @Test
    public void testProbability() throws ZoneHasNoBannersException {
        zone.setBanners(banners);
        Map<Banner, Integer> probabilityMap = new HashMap<Banner, Integer>();
        probabilityMap.put(banner1, 0);
        probabilityMap.put(banner2, 0);
        probabilityMap.put(banner3, 0);
        Integer rounds = 1000000;
        for (Integer i = 0; i < rounds; i++) {
            Banner banner = strategy.chooseBanner(request);
            probabilityMap.put(banner, probabilityMap.get(banner) + 1);
        }
        Float error = 0.02f;
        Integer leftMargin = Math.round((rounds / banners.size()) * (1 - error));
        Integer rightMargin = Math.round((rounds / banners.size()) * (1 + error));
        for (Map.Entry<Banner, Integer> entry : probabilityMap.entrySet()) {
            Assert.assertTrue(entry.getValue().compareTo(leftMargin) >= 0);
            Assert.assertTrue(entry.getValue().compareTo(rightMargin) <= 0);
        }
    }
}
