package com.seanzx.controller;

import com.seanzx.common.ServerResponse;
import com.seanzx.service.IUserService;
import com.seanzx.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : zhaoxin
 * @date : 2019/7/5.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ServerResponse findUser(@PathVariable("id")String id) {
        UserVO user = userService.findUser(id);
        return ServerResponse.ofData(user);
    }

    @PostMapping("/add")
    @ResponseBody
    public ServerResponse addUser(UserVO userVO) {
        return ServerResponse.ofData(userService.addUser(userVO));
    }

}