package com.heatdeath.pabefa.reader;

import com.heatdeath.pabefa.bean.BeanDefinition;
import com.heatdeath.pabefa.bean.BeanReference;
import com.heatdeath.pabefa.bean.PropertyValue;
import com.heatdeath.pabefa.factory.BeanDefinitionRegistry;
import com.heatdeath.pabefa.resource.Resource;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Author:  heatdeath
 * Date:    2019/9/5
 * Desc:
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) throws Exception {
        doLoadBeanDefinitions(resource.getInputStream());
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        // 解析bean
        registerBeanDefinitions(doc);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document doc) throws Exception {
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    private void processBeanDefinition(Element ele) throws Exception {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        registry.registerBeanDefinition(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                // 字段注入
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (!StringUtils.isEmpty(value)) {
                    beanDefinition.addPropertyValue(new PropertyValue(name, value));
                    continue;
                }
                // 依赖 Bean 注入
                String ref = propertyEle.getAttribute("ref");
                beanDefinition.addPropertyValue(new PropertyValue(name, new BeanReference(ref)));
            }
        }
    }
}
