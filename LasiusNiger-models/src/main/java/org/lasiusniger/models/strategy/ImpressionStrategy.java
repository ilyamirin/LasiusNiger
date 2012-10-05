package org.lasiusniger.models.strategy;

import org.lasiusniger.models.Banner;
import org.lasiusniger.models.Request;

/**
 *
 * @author ilyamirin
 */
public interface ImpressionStrategy {

    Banner chooseBanner(Request request);
}
