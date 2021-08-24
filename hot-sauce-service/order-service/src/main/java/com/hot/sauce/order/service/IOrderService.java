package com.hot.sauce.order.service;

import com.hot.sauce.order.model.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder.Yang
 * @since 2021-08-23
 */
public interface IOrderService extends IService<OrderEntity> {

    /**
     * 创建订单
     */
    void saveOrder();
}
