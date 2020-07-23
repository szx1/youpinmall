package com.qianfeng.controller;

import com.qianfeng.service.OrderService;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 接收前端传递的购物车id数组和总金额
     * @return
     */
    @RequestMapping("/add")
    public ResultVo add(@RequestParam(value = "ids[]") Integer [] ids, Double totalMoney, HttpSession session){
        ResultVo resultVo = orderService.addOrder(ids,totalMoney,session);
        return resultVo;
    }
    /**
     * 查询订单详情
     */
    @RequestMapping("/list")
    public ResultVo list(Integer oid){
        ResultVo resultVo = orderService.getOrderList(oid);
        return resultVo;
    }
}
