package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.R;
import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public int setRoleByUserId(String userId) {
        return baseMapper.setRoleByUserId(userId);
    }

    @Override
    public int deleteRoleByUserId(String userId) {
        return baseMapper.deleteRoleByUserId(userId);
    }

    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return baseMapper.getPermissionsByUserId(userId);
    }

    @Override
    public List<User> getUserList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("id", "0");
        wrapper.select("id", "name");
        List<User> users = baseMapper.selectList(wrapper);
        return users;
    }

    @Override
    public boolean addNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int result = baseMapper.insert(user);
        if (result == 0) {
            return false;
        }
        result = setRoleByUserId(user.getId());
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserById(User user) {
        if ("0".equals(user.getId())) {
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int result = baseMapper.updateById(user);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updatePasswordById(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int result = baseMapper.updateById(user);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean removeUserById(String id) {
        if ("0".equals(id)) {
            return false;
        }
        int result = baseMapper.deleteById(id);
        if (result == 0) {
            return false;
        }
        result = deleteRoleByUserId(id);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public User getUserInfoById(String id) {
        if ("0".equals(id)) {
            return null;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("id", "name");
        return baseMapper.selectOne(wrapper);
    }
}
