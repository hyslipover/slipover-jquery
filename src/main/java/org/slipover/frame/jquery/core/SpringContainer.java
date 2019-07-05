package org.slipover.frame.jquery.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class SpringContainer implements ApplicationContextAware, BeanFactoryAware, EmbeddedValueResolverAware {

    private static ApplicationContext applicationContext;

    private static BeanFactory beanFactory;

    private static StringValueResolver stringValueResolver;

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static String getValue(String name) {
        return stringValueResolver.resolveStringValue(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContainer.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringContainer.beanFactory = beanFactory;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        SpringContainer.stringValueResolver = resolver;
    }
}
