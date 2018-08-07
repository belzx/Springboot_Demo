package com.lizhi.mybatis.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lizhi.muitl.datasource.config.DynamicDataSourceContextHolder;
import com.lizhi.mybatis.entity.Message;
import com.lizhi.mybatis.mapper.MessageMapper;
import com.lizhi.mybatis.service.IMessageService;


@Service
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private MessageMapper messageMapper;

	public List<Message> list() {
		
		return messageMapper.list();
	}

	public int count() {
//		DynamicDataSourceContextHolder.used2DataSource();
//		System.out.println( DynamicDataSourceContextHolder.getDataSourceKey());
		return messageMapper.count();
	}

	@Transactional
	public int add(List<Message> as) {
		as.forEach(d ->this.messageMapper.add(d) );
		
		return 0;
	}
	
	
	

}
