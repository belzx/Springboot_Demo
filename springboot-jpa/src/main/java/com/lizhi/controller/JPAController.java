package com.lizhi.controller;

import com.lizhi.bean.Message;
import com.lizhi.repository.MessageRepository;
import com.lizhi.repository.MessageRepositoryByAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 测试jpa 入口
 */
@RestController
@RequestMapping("/jpa")
public class JPAController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageRepositoryByAuto messageRepositoryByAuto;

    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public String getAdminInfo(@PathVariable(value = "id") Integer id)
    {
        //详情可参考书
        Message obj = messageRepository.findOneById(id);

        System.out.println(obj);

        System.out.println("1 ：使用jpa方法名查询：" + messageRepository.findFirst10ByNickNameLike("%司%"));

//        System.out.println(messageRepository.findByNickNameLikeAndIdLike("李",id));

        System.out.println("2 ：使用bean方法上面查询：" + messageRepository.countid(2));

        System.out.println("3 ：使用方法上面注解备注查询：" + messageRepository.countAll());

        System.out.println("4 ：双注解使用update：" + messageRepository.setName("0000",1));
        Page<Message> byAuto = messageRepositoryByAuto.findByAuto(new Message(), new PageRequest(0, 10));

        System.out.println("5 ：使用自定义的CustomRepositroy：" );
        byAuto.forEach(System.out::println);
        return "";
    }

    @RequestMapping(value = "/a",method=RequestMethod.GET)
    public String getAfo()
    {
        System.out.println(111);
        return "123123";
    }
}
