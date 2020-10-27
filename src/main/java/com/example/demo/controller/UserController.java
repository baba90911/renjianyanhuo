package com.example.demo.controller;

import com.example.demo.pojo.JsonData;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/api/user_login"})
@Api(description = "登陆操作")
@CrossOrigin
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public JsonData login(@RequestBody User user){
        System.out.println(user.getPassword());
        if(userService.login(user)){return  JsonData.success();}
        else {return  JsonData.fail("用户名密码错误");}
    }
}
