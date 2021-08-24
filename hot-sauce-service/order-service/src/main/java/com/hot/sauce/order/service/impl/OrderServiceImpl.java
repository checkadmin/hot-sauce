package com.hot.sauce.order.service.impl;

import com.hot.sauce.goods.api.GoodsApiService;
import com.hot.sauce.order.model.entity.OrderEntity;
import com.hot.sauce.order.mapper.OrderMapper;
import com.hot.sauce.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hot.sauce.user.api.UserApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder.Yang
 * @since 2021-08-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @DubboReference
    private GoodsApiService goodsApiService;

    @DubboReference
    private UserApiService userApiService;


    @Override
    public void saveOrder() {
        OrderEntity order = new OrderEntity();
        order.setOrderId(1L);
        order.setGoodsId(1L);
        order.setCreateTime(1L);
        order.setUserId(1L);
        order.setOrderNo(1L);
        orderMapper.insert(order);

        goodsApiService.updateGoods(1);

        userApiService.updateUser(1);

    }
}
