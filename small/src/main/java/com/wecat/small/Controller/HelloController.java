package com.wecat.small.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/10/22.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/one")
    public  String hello(){
        return "hello world";
    }
}
