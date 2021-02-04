package com.example.controller;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/2/1-11:39
 */

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.dto.LoginDto;
import com.example.common.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录接口
 */
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @ResponseBody
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("userName", loginDto.getUsername()));

//        Assert.notNull(user, "用户不存在");
/**
 *出现错误 及时 return
 */
        if (user == null) {
            System.out.println("用户不存在");

            //Assert.notNull(user, "用户不存在");
            return Result.fail("用户不存在", "-2");
        }
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            System.out.println("密码不正确");
            return Result.fail("密码不正确");
        }


        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }


}
