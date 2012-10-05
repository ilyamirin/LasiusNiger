package org.lasiusniger.models;

import java.util.Properties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
/**
 *
 * @author ilyamirin
 */
@ToString
@RequiredArgsConstructor
public class Configuration {
    
    @NonNull
    private Properties properties;
    
    public String get(String property) {
        return properties.getProperty(property);
    }
}
