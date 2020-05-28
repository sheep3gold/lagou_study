package com.yx.config;

import com.yx.pojo.Configuration;
import com.yx.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> listSelect = rootElement.selectNodes("//select");
        elementWrap(configuration,namespace,listSelect);

        List<Element> listInsert = rootElement.selectNodes("//insert");
        elementWrap(configuration, namespace, listInsert);

        List<Element> listDelete = rootElement.selectNodes("//delete");
        elementWrap(configuration, namespace, listDelete);

        List<Element> listUpdate = rootElement.selectNodes("//update");
        elementWrap(configuration, namespace, listUpdate);
    }

    /**
     * 封装语句到configuration
     * @param configuration
     * @param namespace
     * @param list
     */
    public void elementWrap(Configuration configuration,String namespace, List<Element> list) {
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }
}
