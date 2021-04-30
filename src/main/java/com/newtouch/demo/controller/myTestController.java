package com.newtouch.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Component
public class myTestController {
    //http://127.0.0.1:8081/sayHello?name=XIONG
    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        System.out.println("========>");
        return "name="+name+Long.MAX_VALUE;
    }
    @GetMapping("/xx")//@PostMapping("/xx")
    public Map<String,Object> xx(@RequestParam String name){
        System.out.println("name="+name);
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        return map;
    }
    protected String xx(){
        System.out.println("========>11");
        return null;
    }
}
