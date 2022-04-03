package com.example.week05.T1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class GetBean {


    public void get() {

        ClassPathXmlApplicationContext classPathXmlContext = new ClassPathXmlApplicationContext("myBean.xml");
        MyBean myBean1 = classPathXmlContext.getBean("myBean", MyBean.class);


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        MyBean myBean2 = applicationContext.getBean("myBean", MyBean.class);


    }
}
