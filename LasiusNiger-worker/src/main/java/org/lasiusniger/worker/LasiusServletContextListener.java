package org.lasiusniger.worker;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.log4j.Log4j;
import org.lasiusniger.models.Configuration;

/**
 *
 * @author ilyamirin
 */
@Log4j
public class LasiusServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        Properties conf = new Properties();
        InputStream is;
        try {
            is = new FileInputStream("classpath:default.properties");
            conf.load(is);
        } catch (FileNotFoundException ex) {
            log.error("Can`t load properties", ex);
        } catch (IOException ex) {
            log.error("Can`t load properties", ex);
        }
        
        Injector injector = Guice.createInjector(new LasiusModule(new Configuration(conf)));
        return injector;
    }
}
