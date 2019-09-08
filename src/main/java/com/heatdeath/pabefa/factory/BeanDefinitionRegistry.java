package com.heatdeath.pabefa.factory;

import com.heatdeath.pabefa.bean.BeanDefinition;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception;
}
