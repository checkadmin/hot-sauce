package com.hot.sauce.order.mapper;

import com.hot.sauce.order.model.entity.OrderEntity;
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
public interface OrderMapper extends BaseMapper<OrderEntity> {

}
