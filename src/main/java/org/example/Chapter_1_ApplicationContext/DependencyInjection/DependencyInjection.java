package org.example.Chapter_1_ApplicationContext.DependencyInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjection {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("DiMetaData.xml");

    }
}
