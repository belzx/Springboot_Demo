package com.lizhi.muitl.datasource.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = {"com.lizhi.mybatis.mapper"})
public class DataSourceConfig {
	
	
	@Primary // 配置一个默认的，不然启动项目报出出现多个DataSource
	@Bean(name ="d1")
	public DruidDataSource d1() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:mysql://182.254.232.181:3306/lizhixiongdedb?useUnicode=true&characterEncoding=utf-8");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("121929");
		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return druidDataSource;
	}
	
	@Bean(name ="d2")
	public DruidDataSource d2() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:mysql://182.254.232.181:3306/lizhixiongdefastdb?useUnicode=true&characterEncoding=utf-8");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("121929");
		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return druidDataSource;
	}
	

	
    /**
     * Dynamic data source.
     *
     * @return the data source
     */
	@Bean("dynamicDataSource")
	public DynamicDataSource dynamicDataSource(@Qualifier("d1") DruidDataSource d1,@Qualifier("d2") DruidDataSource d2) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object,Object> map = new HashMap();
		map.put( "d1", d1);
		map.put("d2", d2);
		// 将 master 和 slave 数据源作为指定的数据源
		dynamicDataSource.setTargetDataSources(map);
		// 将 master 数据源作为默认指定的数据源
		dynamicDataSource.setDefaultTargetDataSource(d1);
		
		
		DynamicDataSourceContextHolder.dataSourceKeys.addAll(map.keySet());
		DynamicDataSourceContextHolder.dataSourceKeys.remove("d1");
		
		return dynamicDataSource;
		
	}
	
	/**
     * 配置 SqlSessionFactoryBean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到 
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     * 
     * @return the sql session factory bean
     */
    @Bean(name="sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("d1")DruidDataSource d1) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(d1);
        factoryBean.setTypeAliasesPackage("com.lizhi.mybatis.entity");
 
        return factoryBean.getObject();

    }
    
    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
    	
        return new DataSourceTransactionManager(dynamicDataSource);
    }
    
}

