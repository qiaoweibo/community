package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 下面这个两个注解都是springmvc的注解（后面再说）
 * /alpha是给类AlphaController取一个访问的名字（路径），让浏览器可以访问这个类，从而可以访问类中的方法
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    /**
     * sayHello()这个方法可以被浏览器访问到的前提是，加上RequestMapping注解，
     * 给这个方法起一个访问路径hello
     *
     * 注解ResponseBody是为了声明下面的方法返回的是一个字符串，如果不加的话，会默认返回一个网页
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello spring boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

}
