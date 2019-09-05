package com.heatdeath.pabefa.resource;

import lombok.AllArgsConstructor;

import java.io.InputStream;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
@AllArgsConstructor
public class ClassPathResource implements Resource {
    private String fileName;
    private ClassLoader classLoader;

    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(fileName);
    }
}
