package com.yx.sqlSession;

import com.yx.config.XmlConfigerBuilder;
import com.yx.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        XmlConfigerBuilder xmlConfigerBuilder = new XmlConfigerBuilder(configuration);
        Configuration configuration = xmlConfigerBuilder.parseConfig(inputStream);

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
