package org.lasiusniger.models.strategy;

import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.exceptions.ZoneHasNoBannersException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ruslan
 * Date: 07.10.12
 * Time: 9:41
 */
public class TournamentStrategy implements ImpressionStrategy {
    Random randomGenerator = new Random();

    /**
     * @param request - пользовательский запрос
     * @return - один из баннеров с максимальным весом на зоне
     * @throws ZoneHasNoBannersException
     */
    @Override
    public Banner chooseBanner(Request request) throws ZoneHasNoBannersException {
        Set<Banner> banners = request.getZone().getBanners();
        if (banners.size() == 0) {
            throw new ZoneHasNoBannersException(request.getZone());
        }
        if (banners.size() == 1) {
            return (Banner) banners.toArray()[0];
        }
        /**
         * определяем максимальный вес
         */
        Integer maxWeight = 0;
        for (Banner banner : banners) {
            if (banner.getWeight() > maxWeight) {
                maxWeight = banner.getWeight();
            }
        }
        /**
         * выбираем все баннера с максимальным весом
         */
        ArrayList<Banner> bannersWithMaxWeight = new ArrayList<Banner>();
        for (Banner banner : banners) {
            if (banner.getWeight().equals(maxWeight)) {
                bannersWithMaxWeight.add(banner);
            }
        }
        /**
         * возвращаем случайный баннер с максимальным весом
         */
        return bannersWithMaxWeight.get(randomGenerator.nextInt(bannersWithMaxWeight.size()));
    }
}
