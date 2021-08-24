package com.hot.sauce.user.dubbo;

import com.hot.sauce.user.api.UserApiService;
import com.hot.sauce.user.model.entity.UserEntity;
import com.hot.sauce.user.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;

@DubboService(timeout = 3000)
public class UserApiServiceImpl implements UserApiService {
    @Autowired
    private IUserService userService;

    @Override
    public void updateUser(Integer userId) {
        UserEntity user = userService.getById(userId);
        user.setAccount(user.getAccount().subtract(BigDecimal.ONE));
        userService.updateById(user);
    }
}
