package org.lasiusniger.models.strategy;

import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;
import org.lasiusniger.models.exceptions.ZoneHasNoBannersException;

/**
 *
 * @author ilyamirin
 */
public interface ImpressionStrategy {

    Banner chooseBanner(Request request) throws ZoneHasNoBannersException;
}
