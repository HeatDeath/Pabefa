package com.heatdeath.pabefa.service;

import lombok.Setter;

import java.util.Objects;

/**
 * @Author heatdeath
 * @Date 2019/09/05
 * @Desc
 */
@Setter
public class RefBeanService {
    private HelloWorldService helloWorldService;

    public void sayHello() {
        if (Objects.isNull(helloWorldService)) {
            System.out.println("i am RefBeanService, but my helloWorldService is Null");
        } else {
            System.out.println("i am RefBeanService, i get the helloWorldService~~~~~");
        }
    }
}
