package org.lasiusniger.models.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lasiusniger.models.Zone;

/**
 *
 * @author ilyamirin
 */
@RequiredArgsConstructor
public class UnknownZoneException extends Exception {

    @NonNull
    private Zone zone;

    @Override
    public String toString() {
        return "Unknown zone: " + zone.toString();
    }
}
