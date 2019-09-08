package com.heatdeath.pabefa.bean;

import lombok.Data;

/**
 * @Author heatdeath
 * @Date 2019/09/05
 * @Desc Bean 引用
 */
@Data
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }
}
