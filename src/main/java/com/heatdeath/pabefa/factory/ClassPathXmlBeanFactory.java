package com.heatdeath.pabefa.factory;

import com.google.common.collect.Maps;
import com.heatdeath.pabefa.bean.BeanDefinition;
import com.heatdeath.pabefa.bean.BeanReference;
import com.heatdeath.pabefa.reader.BeanDefinitionReader;
import com.heatdeath.pabefa.reader.XmlBeanDefinitionReader;
import com.heatdeath.pabefa.resource.ClassPathResource;
import com.heatdeath.pabefa.resource.Resource;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */
public class ClassPathXmlBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    // L1 cache
    private final Map<String, Object> singletonObjects = Maps.newConcurrentMap();

    // L2 cache
    private Map<String, Object> earlySingletonObjects = Maps.newHashMap();

    // L3 cache
    private Map<String, BeanDefinition> singletonFactories = Maps.newHashMap();


    private BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(this);

    public ClassPathXmlBeanFactory(String configLocation) throws Exception {
        Resource resource = new ClassPathResource(configLocation, this.getClass().getClassLoader());
        beanDefinitionReader.loadBeanDefinitions(resource);
    }

    public Object getBean(String beanName) throws Exception {
        Object bean = singletonObjects.get(beanName);
        if (Objects.isNull(bean)) {
            synchronized(singletonObjects) {
                bean = earlySingletonObjects.get(beanName);
                if (Objects.isNull(bean)) {
                    BeanDefinition beanDefinition = singletonFactories.get(beanName);
                    if (Objects.nonNull(beanDefinition)) {
                        bean = doCreateBean(beanDefinition);
                        earlySingletonObjects.put(beanName, bean);
                        singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return bean;
    }

    /**
     * 注册 beanDefinition（创建 Bean，设置 Bean 的属性）
     * @param beanName
     * @param beanDefinition
     * @throws Exception
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreateBean(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
//        beanDefinition.setBean(bean);
        singletonObjects.put(beanName, bean);
    }

//    public BeanDefinition getBeanDefinition(String beanName) {
//        return beanDefinitionMap.get(beanName);
//    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClazz().newInstance();
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        beanDefinition.getPropertyValues().forEach(propertyValue -> {
            try {
                Field declaredField = beanDefinition.getBeanClazz().getDeclaredField(propertyValue.getFiledName());
                declaredField.setAccessible(true);
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }
                declaredField.set(bean, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

