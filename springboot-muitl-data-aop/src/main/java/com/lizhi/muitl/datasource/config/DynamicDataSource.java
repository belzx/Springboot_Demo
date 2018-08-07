package com.lizhi.muitl.datasource.config;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 该类继承自 AbstractRoutingDataSource 类，
 * 在访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
 * @author lx
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		 return DynamicDataSourceContextHolder.getDataSourceKey();
	}
	
}
