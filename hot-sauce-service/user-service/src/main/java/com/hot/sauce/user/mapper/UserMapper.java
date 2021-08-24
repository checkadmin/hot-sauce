package com.hot.sauce.user.mapper;

import com.hot.sauce.user.model.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuYang
 * @since 2021-08-23
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
