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
 * @ClassName: DataSourceConfig
 * @Description: sale多数据源配置
 * @Author: PANLVZ
 * @Date: 2020/12/16
 */
@Configuration
@MapperScan(basePackages = {"com.example.sale.dao.sale"}, sqlSessionTemplateRef = "saleSessionTemplate")
public class SaleDataSourceConfig {

    @Bean(name = "saleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sale")
    public DataSource saleDataSource() {
        return DataSourceBuilder.create().build();

    }

    @Bean(name = "saleSqlSessionFactory")
    public SqlSessionFactory saleSqlSessionFactory(@Qualifier("saleDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/sale/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "saleTransactionManager")
    public DataSourceTransactionManager saleTransactionManager(@Qualifier("saleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager((dataSource));
    }

    @Bean("saleSessionTemplate")
    public SqlSessionTemplate saleSessionTemplate(@Qualifier("saleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
