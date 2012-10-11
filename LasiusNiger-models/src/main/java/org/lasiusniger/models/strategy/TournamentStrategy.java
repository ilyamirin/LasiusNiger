package org.lasiusniger.models.strategy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;

/**
 * Created with IntelliJ IDEA. User: ruslan Date: 07.10.12 Time: 9:41
 */
public class TournamentStrategy implements ImpressionStrategy {

    Random randomGenerator = new Random();

    /**
     * @param request - пользовательский запрос
     * @return - один из баннеров с максимальным весом на зоне
     * @throws ZoneHasNoBannersException
     */
    @Override
    public Banner chooseBanner(Request request) {
        Set<Banner> banners = request.getZone().getBanners();
        if (banners.isEmpty()) {
            return null;
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
