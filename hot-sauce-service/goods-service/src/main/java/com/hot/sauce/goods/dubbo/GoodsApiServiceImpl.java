package com.hot.sauce.goods.dubbo;

import com.hot.sauce.goods.api.GoodsApiService;
import com.hot.sauce.goods.model.entity.GoodsEntity;
import com.hot.sauce.goods.service.IGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ： liuYang
 * @date ： 2021/8/23 15:18
 * @description ： 商品服务实现类
 */
@DubboService
public class GoodsApiServiceImpl implements GoodsApiService {

    @Autowired
    private IGoodsService goodsService;


    @Override
    public GoodsEntity getGoods(Integer goodsId) {
        return goodsService.getById(goodsId);
    }
}
