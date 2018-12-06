package com.ayang.app.service.impl;

import com.ayang.app.entity.User;
import com.ayang.app.mapper.UserMapper;
import com.ayang.app.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihaoyang123
 * @since 2018-11-15
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean userRegister(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.insert(user);
    }
}

