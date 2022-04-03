package com.example.week05.T1;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactory implements FactoryBean<MyBean> {

    @Override
    public MyBean getObject() throws Exception {
        if (isSingleton()) {
            return create();
        }
        return new MyBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }


    @Override
    public boolean isSingleton() {
        return Boolean.TRUE;
    }

    private MyBean create() {
        // TODO 单例
        return new MyBean();
    }
}
