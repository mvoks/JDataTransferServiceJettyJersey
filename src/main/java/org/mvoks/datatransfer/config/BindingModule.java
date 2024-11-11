package org.mvoks.datatransfer.config;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.mvoks.datatransfer.config.yaml.YamlService;

@Log4j2
public class BindingModule extends AbstractBinder {

    @Override
    protected void configure() {
        log.info("Binding module start");
        bind(YamlService.class).to(YamlService.class).in(Singleton.class);
        log.info("Binding module complete");
    }
}