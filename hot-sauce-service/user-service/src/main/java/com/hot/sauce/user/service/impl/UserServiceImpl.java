package com.hot.sauce.user.service.impl;

import com.hot.sauce.user.model.entity.UserEntity;
import com.hot.sauce.user.mapper.UserMapper;
import com.hot.sauce.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuYang
 * @since 2021-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
