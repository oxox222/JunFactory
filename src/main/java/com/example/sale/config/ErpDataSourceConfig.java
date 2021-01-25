package com.example.sale.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName: ErpDataSourceConfig
 * @Description: erp多数据源配置
 * @Author: PANLVZ
 * @Date: 2020/12/16
 */
@Configuration
@MapperScan(basePackages = {"com.example.sale.dao.erp"}, sqlSessionTemplateRef = "erpSessionTemplate")
public class ErpDataSourceConfig {

    @Bean(name = "erpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.erp")
    public DataSource erpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "erpSessionFactory")
    public SqlSessionFactory erpSessionFactory(@Qualifier("erpDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath:mapper/erp/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "erpTransactionManager")
    public DataSourceTransactionManager erpTransactionManager(@Qualifier("erpDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "erpSessionTemplate")
    public SqlSessionTemplate erpSessionTemplate(@Qualifier("erpSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
