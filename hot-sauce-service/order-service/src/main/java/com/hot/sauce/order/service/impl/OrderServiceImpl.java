package com.hot.sauce.order.service.impl;

import com.hot.sauce.order.model.entity.OrderEntity;
import com.hot.sauce.order.mapper.OrderMapper;
import com.hot.sauce.order.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

}
