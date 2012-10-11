package org.lasiusniger.models.strategy;

import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;

import java.util.Random;

/**
 * @author ilyamirin
 */
public class RandomStrategy implements ImpressionStrategy {

    private Random randomGenerator = new Random();

    public Banner chooseBanner(Request request) {
        Object[] banners = request.getZone().getBanners().toArray();
        int count = banners.length;
        if (count == 0) {
            return null;
        }
        return (Banner) banners[randomGenerator.nextInt(count)];
    }
}
