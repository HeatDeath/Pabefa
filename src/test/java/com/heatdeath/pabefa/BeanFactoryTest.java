package com.heatdeath.pabefa;

import com.heatdeath.pabefa.factory.BeanFactory;
import com.heatdeath.pabefa.factory.ClassPathXmlBeanFactory;
import com.heatdeath.pabefa.service.HelloWorldService;
import org.junit.Test;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */
public class BeanFactoryTest {
    @Test
    public void test() throws Exception {
        BeanFactory beanFactory = new ClassPathXmlBeanFactory("applicationCtx.xml");
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }
}
