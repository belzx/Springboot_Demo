package com.lizhi.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * flyat
 * Created by yadong.zhang on com.blog.app.core.config
 * User锛歽adong.zhang
 * Date锛�2016/12/6 15:00
 * @Modify by yadong.zhang
 *      璇︽儏璇风湅applaction.properties鏂囦欢涓殑閰嶇疆
 */
@Component
//鎸囧畾鎵弿鐨刴apper鎺ュ彛鎵�鍦ㄧ殑鍖�
@MapperScan("com.zyd.testMybatis.mapper")
public class AppMybatisConfiguration {
	
	
}
