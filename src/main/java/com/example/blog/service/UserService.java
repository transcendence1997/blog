package com.example.blog.service;

import com.example.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
public interface UserService extends IService<User> {

    int setRoleByUserId(String userId);

    int deleteRoleByUserId(String userId);

    List<String> getPermissionsByUserId(String userId);

    List<User> getUserList();

    boolean addNewUser(User user);

    boolean updateUserById(User user);

    boolean updatePasswordById(User user);

    boolean removeUserById(String id);

    User getUserInfoById(String id);
}
