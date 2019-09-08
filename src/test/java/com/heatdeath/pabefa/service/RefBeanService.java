package com.heatdeath.pabefa.service;

/**
 * @Author heatdeath
 * @Date 2019/09/05
 * @Desc
 */
public class RefBeanService {
    private HelloWorldService helloWorldService;

    public void sayHello() {
        System.out.println("this is refBeanService~");
        helloWorldService.sayHello();
    }
}
