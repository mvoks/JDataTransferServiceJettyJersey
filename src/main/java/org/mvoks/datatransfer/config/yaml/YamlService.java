package org.mvoks.datatransfer.config.yaml;

import java.io.InputStream;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jvnet.hk2.annotations.Service;
import org.yaml.snakeyaml.Yaml;

@Singleton
@Service
@Getter
@Log4j2
public class YamlService {

    private static final String YAML_FILE = "application.yaml";
    private YamlProperties yamlProperties;

    @PostConstruct
    void init() {
        log.info("Loading application properties from '{}' file", YAML_FILE);
        final Yaml yaml = new Yaml();
        try {
            final InputStream inputStream = yaml.getClass()
                .getClassLoader()
                .getResourceAsStream(YAML_FILE);
            yamlProperties = yaml.loadAs(inputStream, YamlProperties.class);
            inputStream.close();
            log.info("Loaded application properties from yaml file\n{}", yamlProperties);
        } catch (final Exception ex) {
            log.error("Failed to load application properties from yaml file", ex);
        }
    }
}