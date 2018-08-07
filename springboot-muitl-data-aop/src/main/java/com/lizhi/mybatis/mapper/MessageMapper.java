package com.lizhi.mybatis.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lizhi.mybatis.entity.Message;
import com.lizhi.mybatis.util.MyMapper;

import java.util.List;

/**
 * @author zhangyd
 * @version V1.0
 * @Description
 * @date 2017年3月10日 下午2:42:45
 * @modify
 * @Review
 * @since JDK ： 1.7
 */
@Mapper
public interface MessageMapper extends MyMapper<Message> {
    List<Message> list();

    int count();

	void add(@Param("mes")Message mes);
}
