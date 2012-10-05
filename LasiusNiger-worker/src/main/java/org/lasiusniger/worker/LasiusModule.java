package org.lasiusniger.worker;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lasiusniger.models.Configuration;
import org.lasiusniger.worker.servlets.*;

/**
 *
 * @author ilyamirin
 */
@RequiredArgsConstructor
public class LasiusModule extends ServletModule {

    @NonNull
    private Configuration conf;

    @Override
    protected void configureServlets() {
        install(new JpaPersistModule("LasiusJpaUnit"));
        filter("*/*").through(PersistFilter.class);
        
        bind(Configuration.class).toInstance(conf);
        
        serve("*/impression").with(LasuisImpressionServlet.class);
        serve("*/click").with(LasuisClickServlet.class);
    }
}
