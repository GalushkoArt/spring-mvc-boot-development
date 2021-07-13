package com.db.work_application.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppContext extends AnnotationConfigWebApplicationContext {
    public WebAppContext() {
        register(JavaConfig.class);
    }
}