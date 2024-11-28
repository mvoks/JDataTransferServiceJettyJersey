package org.mvoks.datatransfer.config;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.log4j.Log4j2;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.mvoks.datatransfer.config.em.FactoryEntityManager;
import org.mvoks.datatransfer.config.em.FactoryEntityManagerFactory;
import org.mvoks.datatransfer.config.yaml.YamlService;
import org.mvoks.datatransfer.dao.user.UserDao;
import org.mvoks.datatransfer.mapper.UserMapper;
import org.mvoks.datatransfer.mapper.UserMapperImpl;
import org.mvoks.datatransfer.mapper.UserPasswordUpdateMapper;
import org.mvoks.datatransfer.mapper.UserPasswordUpdateMapperImpl;
import org.mvoks.datatransfer.mapper.UserRegistrationMapper;
import org.mvoks.datatransfer.mapper.UserRegistrationMapperImpl;
import org.mvoks.datatransfer.security.JwtAccessService;
import org.mvoks.datatransfer.security.JwtHelper;
import org.mvoks.datatransfer.security.JwtRefreshService;
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
        bind(JwtHelper.class).to(JwtHelper.class).in(Singleton.class);
        bind(JwtAccessService.class).to(JwtService.class).named("jwtAccessService");
        bind(JwtRefreshService.class).to(JwtService.class).named("jwtRefreshService");
        bind(AuthServiceImpl.class).to(AuthService.class);
        bindFactory(FactoryEntityManagerFactory.class).to(EntityManagerFactory.class).in(Singleton.class);
        bindFactory(FactoryEntityManager.class).to(EntityManager.class);
        bind(UserDao.class).to(UserDao.class);
        bind(UserServiceImpl.class).to(UserService.class);
        bind(UserMapperImpl.class).to(UserMapper.class);
        bind(UserPasswordUpdateMapperImpl.class).to(UserPasswordUpdateMapper.class);
        bind(UserRegistrationMapperImpl.class).to(UserRegistrationMapper.class);
        log.info("Binding module complete");
    }
}