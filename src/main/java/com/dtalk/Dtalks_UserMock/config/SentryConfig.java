package com.dtalk.Dtalks_UserMock.config;

import io.sentry.Sentry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "prod"})
public class SentryConfig {

    @Value("${sentry.dsn}")
    private String sentryDsn;

    @Value("${sentry.environment}")
    private String environment;

    @Value("${sentry.servername}")
    private String serverName;

    @PostConstruct
    public void initSentry() {
        Sentry.init(options -> {
            options.setDsn(sentryDsn);
            options.setEnvironment(environment);
            options.setServerName(serverName);
        });
    }
}
