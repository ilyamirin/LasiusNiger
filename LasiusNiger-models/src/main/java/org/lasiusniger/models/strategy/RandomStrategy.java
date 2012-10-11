package org.lasiusniger.models.strategy;

import java.util.Random;
import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;

/**
 *
 * @author ilyamirin
 */
public class RandomStrategy implements ImpressionStrategy {

    private Random r = new Random();

    public Banner chooseBanner(Request request) {
        Object[] banners = request.getZone().getBanners().toArray();
        int count = banners.length;
        if (count == 0) {
            return null;
        }
        return (Banner) banners[r.nextInt(count)];
    }
}
