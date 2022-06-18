package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    @RequestMapping("http")
    public void http(HttpServletRequest request, HttpServletResponse response) {

        //request用法
        //获取请求数据(请求方式、请求路径、请求行{请求行有很多数据，这里做了一个封装})
        /**
         * 下面两行是请求行
         * @author QiaoWeiBo
         * @date 2022/6/17 4:43 PM
         */
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());

        /**
         * 下面是消息头
         * @author QiaoWeiBo
         * @date 2022/6/17 4:42 PM
         */
        //request.getHeaderNames();是获得所有请求行的key，请求行是key，value结构,
        // 得到的是一个迭代器,功能和iterator类似，就是比iterator老
        // (其实Enumeration已经不推荐使用了，但是request对象很早就有了，一直用的Enumeration)
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            //nextElement()是获取当前元素
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }

        /**
         * 请求体
         * 传参数进来，获取值，结合http这块的八股就懂了
         *
         * 下面的业务数据参数是null，怎传参？在浏览器路径后面拼一个?code=123，?表示后面带参数，code是参数名，123是参数值
         * 如果再来一个参数，中间加上&，
         * @author QiaoWeiBo
         * @date 2022/6/17 4:44 PM
         */
        System.out.println(request.getParameter("code"));


        //response用法
        //返回响应数据
        // (需要设置一下，是返回网页还是返回字符串，还是图片)text/html表示网页类型，charset是字符集
        response.setContentType("text/html;charset=utf-8");
        //要想让response向浏览器响应，就是通过response里面封装的输出流(response.getWriter();)
        //这个流用完之后正常是要用finally关掉，但是java7中有一个语法，在try后面加一个()，将流放到括号里，
        // 编译的时候，会自动的加一个finally来把流关掉{前提是这个流有close方法}
        try (
                PrintWriter writer = response.getWriter();
        ) {
            //通过writer向浏览器打印网页
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Get请求
    //方式一
    //路径：/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {

        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //方式二
    //路径：/student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //Post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    //方法一
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", 30);
        modelAndView.setViewName("/demo/view");

        return modelAndView;
    }

    //方法二{就返回String了，简化了，return的就是view的路径}
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    //响应JSON数据(异步请求)
    //Java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }

}
