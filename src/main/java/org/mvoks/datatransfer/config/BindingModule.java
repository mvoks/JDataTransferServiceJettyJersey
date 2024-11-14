package org.mvoks.datatransfer.config;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.mvoks.datatransfer.config.yaml.YamlService;
import org.mvoks.datatransfer.security.JwtService;
import org.mvoks.datatransfer.service.AuthService;
import org.mvoks.datatransfer.service.UserService;
import org.mvoks.datatransfer.service.impl.AuthServiceImpl;
import org.mvoks.datatransfer.service.impl.UserServiceImpl;

@Log4j2
public class BindingModule extends AbstractBinder {

    @Override
    protected void configure() {
        log.info("Binding module start");
        bind(YamlService.class).to(YamlService.class).in(Singleton.class);
        bind(JwtService.class).to(JwtService.class);
        bind(AuthServiceImpl.class).to(AuthService.class);
        bind(UserServiceImpl.class).to(UserService.class);
        log.info("Binding module complete");
    }
}