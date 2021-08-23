package com.hot.sauce.goods.api;

import com.hot.sauce.goods.model.entity.GoodsEntity;

/**
 * @author ： liuYang
 * @date ： 2021/8/23 15:16
 * @description ：商品服务api
 */
public interface GoodsApiService {
    /**
     * 获取商品信息
     * @param goodsId
     */
    GoodsEntity getGoods(Integer goodsId);


}