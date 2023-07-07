package com.java.fullProject.someConceptTesting.springActuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyCustomValuesUnderInfoEndpoint implements InfoContributor {

    @Autowired
    private Environment environment;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("port", environment.getProperty("local.server.port"));
        builder.withDetail("profiles", environment.getDefaultProfiles());
    }

/*   Add this plugin for git information in /info
     <plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
                <version>2.2.1</version>
            </plugin> */
}