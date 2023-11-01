package org.example.Chapter_1_ApplicationContext.instantiation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorInstantiation {

    private static final Log log = LogFactory.getLog(ConstructorInstantiation.class);

    public static void main(String[] args) {
        // instantiation with constructor
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        log.info(ctx.getBean("person", Person.class));


        // following two is not widely used

        // instantiation with static-factory method
        log.info(ctx.getBean("staticFactoryMethod",StaticFactoryMethod.class));

        // instantiation with instatnce-factory method
        log.info(ctx.getBean("instanceFactoryMethod",InstanceFactoryMethod.class));

    }
}
