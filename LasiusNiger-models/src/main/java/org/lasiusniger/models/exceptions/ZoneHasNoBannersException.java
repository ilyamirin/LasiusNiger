package org.lasiusniger.models.exceptions;

import lombok.ToString;
import org.lasiusniger.models.Zone;

/**
 *
 * @author ilyamirin
 */
@ToString
public class ZoneHasNoBannersException extends Exception {
    
    private Zone zone;

    public ZoneHasNoBannersException(Zone zone) {
        super();
        this.zone = zone;
    }
    
    @Override
    public String getMessage() {
        return "Zone have no banners: " + zone.toString();
    }

}
