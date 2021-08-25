package com.hot.sauce.order.controller;


import com.hot.sauce.base.service.annotation.Idempotent;
import com.hot.sauce.base.service.result.ResultBody;
import com.hot.sauce.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder.Yang
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/save")
    @Idempotent
    public ResultBody save(){
        orderService.saveOrder();
        return ResultBody.success();
    }
}