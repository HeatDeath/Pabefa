package com.heatdeath.pabefa.service;

import lombok.Setter;

/**
 * Author:  heatdeath
 * Date:    2019/9/4
 * Desc:
 */

@Setter
public class HelloWorldService {
    private String text;
    private RefBeanService refBeanService;

    public void sayHello() {
        System.out.println(text);
        refBeanService.sayHello();
    }
}
