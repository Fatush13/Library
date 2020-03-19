package com.fatush.library.event;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object userName = event.getAuthentication().getPrincipal();
        Object credentials = event.getAuthentication().getCredentials();
        logger.warn("Failed to login using username \"" + userName + "\"");
        logger.warn("Failed to login using password \"" + credentials + "\"");
    }
}