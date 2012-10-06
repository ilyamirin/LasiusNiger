package org.lasiusniger.models.strategy;

import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.exceptions.ZoneHasNoBannersException;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author ilyamirin
 */
public class RouletteStrategy implements ImpressionStrategy {

    Random randomGenerator = new Random();

    /**
     * Строит карту весов баннеров, где "площадь", занимаемая баннером
     * прямо пропорциональная его весу, случайно выбирает точку на этой карте и
     * возвращает баннер, к оторому эта точка привязана.
     * Таким образом, чем выше вес баннера, тем ыше вероятность его показа.
     * @param request - пользовательский запрос
     * @return - случайно выбранный баннер
     * @throws ZoneHasNoBannersException
     */
    public Banner chooseBanner(Request request) throws ZoneHasNoBannersException {
        Integer totalWeight = 0;
        Integer bannerWeight;
        /**
         * карта весов
         */
        Map<Integer, Banner> weightMap = new TreeMap<Integer, Banner>();
        Set<Banner> banners = request.getZone().getBanners();
        /**
         * TODO: отфильтровать баннера (уже просмотренные, не подходящие по ограничениям)
         */
        if (banners.size() == 0) {
            throw new ZoneHasNoBannersException(request.getZone());
        }
        /**
         * если баннер всего один, не надо играться с весами и вероятностями
         */
        if(banners.size() == 1) {
            return (Banner)banners.toArray()[0];
        }
        /**
         * заполняем карту отрезками, длины которых равны весам баннеров
         */
        for (Banner banner : request.getZone().getBanners()) {
            bannerWeight = banner.getWeight();
            weightMap.put(totalWeight, banner);
            totalWeight += bannerWeight;
            weightMap.put(totalWeight - 1, banner);
        }
        Integer randomValue = randomGenerator.nextInt(totalWeight);
        Integer key = 0;
        /**
         * генерим случайное число и смотрим, в отрезок какого баннера оно попало
         */
        for (Map.Entry<Integer, Banner> entry : weightMap.entrySet()) {
            if (randomValue <= entry.getKey()) {
                key = entry.getKey();
                break;
            }
        }
        return weightMap.get(key);
    }
}
