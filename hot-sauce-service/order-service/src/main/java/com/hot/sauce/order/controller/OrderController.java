package com.hot.sauce.order.controller;


import com.hot.sauce.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuYang
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/order/orderEntity")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/save")
    public String save(){
        orderService.saveOrder();
        return "SUCCESS";
    }
}