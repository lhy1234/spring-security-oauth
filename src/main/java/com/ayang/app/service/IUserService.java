package com.ayang.app.service;


import com.ayang.app.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihaoyang123
 * @since 2018-11-15
 */
public interface IUserService extends IService<User> {


    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean userRegister(User user);
}
