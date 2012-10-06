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
 * Date: 07.10.12
 * Time: 10:17
 */
public class TournamentStrategyTest {
    Client client;
    Zone zone = new Zone();
    Set<Banner> banners;
    Request request = new Request();
    Banner banner1 = new Banner();
    Banner banner2 = new Banner();
    Banner banner3 = new Banner();
    Banner banner4 = new Banner();
    Banner banner5 = new Banner();
    TournamentStrategy strategy = new TournamentStrategy();

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

        banner4.setUUID("4");
        banner4.setWeight(100);
        banner4.setUser(client);
        banners.add(banner4);

        banner5.setUUID("5");
        banner5.setWeight(100);
        banner5.setUser(client);
        banners.add(banner5);

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
     * при турнирнйо стратегии показы должны равномерно
     * распеределится (с точночтью до ошибки error) между
     * баннерами с аксимальным весом
     *
     * @throws ZoneHasNoBannersException
     */
    @Test
    public void testProbability() throws ZoneHasNoBannersException {
        zone.setBanners(banners);
        Map<Banner, Integer> probabilityMap = new HashMap<Banner, Integer>();
        Integer rounds = 1000000;
        for (Integer i = 0; i < rounds; i++) {
            Banner banner = strategy.chooseBanner(request);
            probabilityMap.put(banner, (probabilityMap.containsKey(banner) ? probabilityMap.get(banner) : 0) + 1);
        }
        Float error = 0.02f;
        Integer leftMargin = Math.round((rounds / probabilityMap.size()) * (1 - error));
        Integer rightMargin = Math.round((rounds / probabilityMap.size()) * (1 + error));

        /**
         * баннера с весом меньше, чем максимальный, не показались ни разу
         */
        Assert.assertNull(probabilityMap.get(banner1));
        Assert.assertNull(probabilityMap.get(banner2));

        /**
         * баннера с максимальным весом показались примерно одинаковое количество раз
         */
        Assert.assertTrue(probabilityMap.get(banner3).compareTo(leftMargin) > 0);
        Assert.assertTrue(probabilityMap.get(banner3).compareTo(rightMargin) < 0);

        Assert.assertTrue(probabilityMap.get(banner4).compareTo(leftMargin) > 0);
        Assert.assertTrue(probabilityMap.get(banner4).compareTo(rightMargin) < 0);

        Assert.assertTrue(probabilityMap.get(banner5).compareTo(leftMargin) > 0);
        Assert.assertTrue(probabilityMap.get(banner5).compareTo(rightMargin) < 0);
    }
}
