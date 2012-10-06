package org.lasiusniger.models.strategy;

import java.util.Random;
import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.exceptions.ZoneHasNoBannersException;

/**
 *
 * @author ilyamirin
 */
public class RandomStrategy implements ImpressionStrategy {
    
    private Random r = new Random();

    public Banner chooseBanner(Request request) throws ZoneHasNoBannersException {
        Object[] banners = request.getZone().getBanners().toArray();
        int count = banners.length;
        if (count == 0) {
            throw new ZoneHasNoBannersException(request.getZone());
        }
        return (Banner) banners[r.nextInt(count)];
    }
    
}
