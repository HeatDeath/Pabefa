package com.heatdeath.pabefa.service;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */
public class HelloWorldService {
    private String text;
    private RefBeanService refBeanService;

    public void sayHello() {
        System.out.println(text);
        refBeanService.sayHello();
    }
}
