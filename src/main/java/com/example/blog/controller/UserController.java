package com.example.blog.controller;


import com.example.blog.common.R;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("info/{id}")
    @PreAuthorize("hasAuthority('User Management')")
    public R getUserInfoById(@PathVariable String id) {
        return R.success().data("item", userService.getUserInfoById(id));
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('User Management')")
    public R getUserList() {
        return R.success().data("items", userService.getUserList());
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('User Management')")
    public R addUser(@RequestBody User user) {
        boolean flag = userService.addNewUser(user);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('User Management')")
    public R updateUser(@RequestBody User user) {
        boolean flag = userService.updateUserById(user);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('User Management')")
    public R deleteUser(@PathVariable String id) {
        boolean flag = userService.removeUserById(id);
        if (flag) {
            return R.success();
        } else {
            return R.error();
        }
    }

}

