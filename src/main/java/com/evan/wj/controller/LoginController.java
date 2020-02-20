package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import java.util.Objects;


@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        //验证账号和密码
        User user = userService.get(username,requestUser.getPassword());
        //如果用户名和密码不正确，则返回400；正确返回200
        if(null == user){
            return new Result(400);
        }else{
            return new Result(200);
        }

//        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//            return new Result(200);
//        }
    }
}

