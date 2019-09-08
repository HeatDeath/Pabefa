package com.heatdeath.pabefa.factory;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */
public interface BeanFactory {
    Object getBean(String beanName) throws Exception;
}
