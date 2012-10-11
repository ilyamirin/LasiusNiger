package org.lasiusniger.models.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lasiusniger.models.Request;

/**
 *
 * @author ilyamirin
 */
@RequiredArgsConstructor
public class NoBannerForRequestException extends Exception {

    @NonNull
    private Request request;

    @Override
    public String toString() {
        return "Zero banners found for request:" + request.toString();
    }
}
