package org.lasiusniger.models.strategy;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Client;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.Zone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: ruslan
 * Date: 06.10.12
 * Time: 15:00
 */
public class RouletteStrategyTest {
    Client client = new Client();
    Zone zone = new Zone();
    Set<Banner> banners;
    Request request = new Request();
    Banner banner1 = new Banner();
    Banner banner2 = new Banner();
    Banner banner3 = new Banner();
    RouletteStrategy strategy = new RouletteStrategy();

    @Before
    public void setUp() throws Exception {
        /**
         * создаем баннера, зону, клиента
         */
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

    @Test
    public void testChooseBanner() {
        zone.setBanners(banners);
        Banner banner = strategy.chooseBanner(request);
        Assert.assertTrue(banners.contains(banner));
    }

    /**
     * проверяем, что вес баннера напрямую влияет на вероятность
     * его показа. чем больше вес, тем больше показов
     * @throws ZoneHasNoBannersException
     */
    @Test
    public void testProbability() {
        zone.setBanners(banners);
        Map<Banner, Integer> probabilityMap = new HashMap<Banner, Integer>();
        probabilityMap.put(banner1, 0);
        probabilityMap.put(banner2, 0);
        probabilityMap.put(banner3, 0);
        for (Integer i = 0; i < 1000000; i++) {
            Banner banner = strategy.chooseBanner(request);
            probabilityMap.put(banner, probabilityMap.get(banner) + 1);
        }
        Assert.assertTrue(probabilityMap.get(banner1) < probabilityMap.get(banner2));
        Assert.assertTrue(probabilityMap.get(banner2) < probabilityMap.get(banner3));
    }
}
