package com.heatdeath.pabefa.factory;

import com.google.common.collect.Maps;
import com.heatdeath.pabefa.bean.BeanDefinition;
import com.heatdeath.pabefa.reader.BeanDefinitionReader;
import com.heatdeath.pabefa.reader.XmlBeanDefinitionReader;
import com.heatdeath.pabefa.resource.ClassPathResource;
import com.heatdeath.pabefa.resource.Resource;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */
public class ClassPathXmlBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = Maps.newConcurrentMap();
    private BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(this);

    public ClassPathXmlBeanFactory(String configLocation) throws Exception {
        Resource resource = new ClassPathResource(configLocation, this.getClass().getClassLoader());
        beanDefinitionReader.loadBeanDefinitions(resource);
    }

    public ClassPathXmlBeanFactory(Resource resource) throws Exception {
        beanDefinitionReader.loadBeanDefinitions(resource);
    }

    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreateBean(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClazz().newInstance();
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        beanDefinition.getPropertyValues().forEach(propertyValue -> {
            try {
                Field declaredField = beanDefinition.getBeanClazz().getDeclaredField(propertyValue.getFiledName());
                declaredField.setAccessible(true);
                declaredField.set(bean, propertyValue.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}

