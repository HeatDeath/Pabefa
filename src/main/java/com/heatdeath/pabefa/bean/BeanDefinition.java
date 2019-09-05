package com.heatdeath.pabefa.bean;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */

@Data
@NoArgsConstructor
public class BeanDefinition {
    private Object bean;

    private String beanClassName;

    private Class beanClazz;

    private Set<PropertyValue> propertyValues = Sets.newHashSet();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    public void setBeanClassName(String beanClassName) throws ClassNotFoundException {
        this.beanClassName = beanClassName;
        this.beanClazz = Class.forName(beanClassName);
    }
}
