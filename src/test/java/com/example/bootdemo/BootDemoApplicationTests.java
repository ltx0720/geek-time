package com.example.bootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;

@SpringBootTest
class BootDemoApplicationTests {


//    @Resource
//    private MyBeanFactory myBeanFactory;


    @Test
    void contextLoads() throws Exception {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableListableBeanFactory beanFactory1 = applicationContext.getBeanFactory();

        MyBeanFactory beanFactory = (MyBeanFactory) beanFactory1.getBean("myBeanFactory");


//        MyBean object = beanFactory.getObject();

        System.out.printf("");
    }

}
