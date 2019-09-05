package com.heatdeath.pabefa.reader;

import com.heatdeath.pabefa.resource.Resource;

import java.io.InputStream;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(Resource resource) throws Exception;
}
