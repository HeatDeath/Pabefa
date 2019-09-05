package com.heatdeath.pabefa.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
