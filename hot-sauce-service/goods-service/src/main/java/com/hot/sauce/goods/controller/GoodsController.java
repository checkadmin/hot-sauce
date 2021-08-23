package com.hot.sauce.goods.controller;


import com.hot.sauce.goods.model.entity.GoodsEntity;
import com.hot.sauce.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuYang
 * @since 2021-08-19
 */
@RestController
@RequestMapping("/goods/goodsEntity")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;


    @GetMapping("/get")
    public List<GoodsEntity> getGoods(){
       return goodsService.list();
    }

}

