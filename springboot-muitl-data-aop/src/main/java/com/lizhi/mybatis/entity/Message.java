package com.lizhi.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = 2128346393707857300L;

	private Integer id;
	private String nickName;
	private String ip;
	private Date insertTime;

	
	
	public Message() {
		super();
	}

	public Message(Integer id, String nickName, String ip, Date insertTime) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.ip = ip;
		this.insertTime = insertTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}
