package com.db.work_application;

import com.db.work_application.config.JavaConfig;
import com.db.work_application.controller.ClientController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ClientController controller = context.getBean(ClientController.class);
        controller.createClient("Petya", "Ivanov");
        controller.createClient("Masha", "Ivanova");
        System.out.println(controller.findById(1));
        System.out.println(controller.getAll());
    }
}
