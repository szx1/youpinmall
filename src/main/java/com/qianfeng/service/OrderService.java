package com.qianfeng.service;

import com.qianfeng.util.ResultVo;

import javax.servlet.http.HttpSession;

public interface OrderService {
    /**
     * 1. 插入订单主表
     * 2. 插入订单详情表
     * @param ids
     * @param totalMoney
     * @param session
     * @return
     */
    ResultVo addOrder(Integer[] ids, Double totalMoney, HttpSession session);

    /**
     * 根据订单主表id查询所有详情
     * @param oid
     * @return
     */
    ResultVo getOrderList(Integer oid);
}
