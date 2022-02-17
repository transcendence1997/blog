package com.example.blog.mapper;

import com.example.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
public interface UserMapper extends BaseMapper<User> {

    int setRoleByUserId(String userId);

    int deleteRoleByUserId(String userId);

    List<String> getPermissionsByUserId(String userId);
}
