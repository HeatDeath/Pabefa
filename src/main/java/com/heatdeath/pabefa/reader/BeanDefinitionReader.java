package com.heatdeath.pabefa.reader;

import com.heatdeath.pabefa.resource.Resource;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(Resource resource) throws Exception;
}
