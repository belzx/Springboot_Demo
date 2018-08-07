package com.lizhi.mybatis.service;

import java.util.List;

import com.lizhi.mybatis.entity.Message;


public interface IMessageService {

	List<Message> list();

	int count();
	
	int add(List<Message> as);

}
