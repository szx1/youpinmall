package com.qianfeng.service;

import com.qianfeng.dao.*;
import com.qianfeng.pojo.*;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo addOrder(Integer[] ids, Double totalMoney, HttpSession session) {
        Users loginUser = (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            return ResultVo.error("未登录");
        }else{
            // 1. 订单主表添加数据(时间、用户id  价格)
            Orders orders = new Orders();
            orders.setCreatetime(new Date());
            orders.setOrderPrice(totalMoney);
            orders.setUid(loginUser.getUid());
            //orders对象执行此添加之前oid没有值，在插入数据之后就有值了
            ordersMapper.addOrder(orders);
            // 2. 订单详情表添加数据(订单主表id、商品id、商品数量)
            for(Integer id:ids){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOid(orders.getOid());
                //根据购物车的id查询购物车详情，获取商品id和购买的商品数量
                Shopping shopping = shoppingMapper.selectByPrimaryKey(id);
                orderDetail.setNum(shopping.getNum());
                orderDetail.setBid(shopping.getBid());
                orderDetailMapper.addOrderDetail(orderDetail);
            }
            //返回订单主表的oid(便于前端传参查询)
            return ResultVo.success("订单生成",orders.getOid());
        }
    }

    @Override
    public ResultVo getOrderList(Integer oid) {
        List<OrderDetail> orderDetailList = orderDetailMapper.getOrderDetailListByoid(oid);
        for (OrderDetail orderDetail:orderDetailList){
            Product product = productMapper.selectByPrimaryKey(orderDetail.getBid());
            orderDetail.setProduct(product);
        }
        return ResultVo.success("success",orderDetailList);
    }
}
