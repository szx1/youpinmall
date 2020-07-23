package com.qianfeng.service;


import com.qianfeng.dao.ProductMapper;
import com.qianfeng.dao.ShoppingMapper;
import com.qianfeng.pojo.Product;
import com.qianfeng.pojo.Shopping;
import com.qianfeng.pojo.Users;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加购物车
     * @param shopping
     * @param session
     * @return
     */
    @Override
    public ResultVo addShopping(Shopping shopping, HttpSession session) {
        //先从session中获取用户对象,查看是否为空
        Users loginUser = (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            //未登录
            return ResultVo.error("用户未登录,请登录,再购买");
        }else{
            //如果当前用户没有购买过此商品，购物车中直接添加数据
            //如果当前用户购买过此商品，购物车中更新数量
            shopping.setUid(loginUser.getUid());
            //1. 先根据用户id和商品id查询购物车对象是否存在
            Shopping oldShopping = shoppingMapper.getShoppingByuidAndbid(shopping);
            //2. 判断oldShopping是否为空
            if(oldShopping==null){
                shoppingMapper.addShopping(shopping);
            }else{
                //购买过此商品，要做数量更新
                shopping.setNum(shopping.getNum()+oldShopping.getNum());
                shoppingMapper.updateCountByuidAndbid(shopping);
            }
            return ResultVo.success("购物车数据添加成功");
        }
    }

    /**
     * 获取当前用户的所有购物车
     * @param session
     * @return
     */
    @Override
    public ResultVo getShoppingList(HttpSession session) {
        Users loginUser = (Users)session.getAttribute("loginUser");
        if(loginUser==null){
            return ResultVo.error("用户未登录，请登录");
        }else{
            List<Shopping> shoppingList = shoppingMapper.getShoppingListByuid(loginUser.getUid());
            //查询购物车所对应的商品的数据
            for (Shopping shopping:shoppingList){
                Product product = productMapper.selectByPrimaryKey(shopping.getBid());
                shopping.setProduct(product);
            }
            return ResultVo.success("success",shoppingList);
        }
    }

    @Override
    public ResultVo deleteShopping(Integer sid,HttpSession session) {
        Users loginUser = (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            //未登录
            return ResultVo.error("用户未登录,请登录");
        }else{
            shoppingMapper.deleteByPrimaryKey(sid);
            return ResultVo.success("删除成功");
            }
        }

    @Override
    public ResultVo changeShoppingNum(Integer sid, Integer flag, HttpSession session) {
        Users loginUser = (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            //未登录
            return ResultVo.error("用户未登录,请登录");
        }else{
            Shopping oldShopping = shoppingMapper.selectByPrimaryKey(sid);
            if(flag==0){
                oldShopping.setNum(oldShopping.getNum()-1);
                shoppingMapper.updateByPrimaryKey(oldShopping);
            }
            else{
                oldShopping.setNum(oldShopping.getNum()+1);
                shoppingMapper.updateByPrimaryKey(oldShopping);
            }
            return ResultVo.success("操作成功");
        }
    }
}
