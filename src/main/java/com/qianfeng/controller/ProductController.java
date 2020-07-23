package com.qianfeng.controller;

import com.qianfeng.pojo.Product;
import com.qianfeng.pojo.Shopping;
import com.qianfeng.service.ProductService;
import com.qianfeng.service.ShoppingService;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingService shoppingService;
    /**
     * 根据类型查询所有图书
     */
    @RequestMapping("/getListById")
    public ResultVo getListByTypeId(Integer tid){
        System.out.println(tid);
        List<Product> productList = productService.getListByTypeId(tid);
        return ResultVo.success("success",productList);
    }
    /**
     * 根据图书id查询图书详情
     */
    @RequestMapping("/getProductById")
    public ResultVo getProductById(Integer bid){
        Product product = productService.getProductById(bid);
        return ResultVo.success("success",product);
    }
    /**
     * 添加到购物车
     */
    @RequestMapping("/addShopping")
    public ResultVo addShopping(@RequestBody Shopping shopping, HttpSession session){
        ResultVo resultVo = shoppingService.addShopping(shopping,session);
        return resultVo;
    }
    /**
     * 根据用户id查询购物车列表
     */
    @RequestMapping("/getShoppingList")
    public ResultVo getShoppingList(HttpSession session){
        ResultVo resultVo = shoppingService.getShoppingList(session);
        return resultVo;
    }

    @RequestMapping("/deleteShopping")
    public ResultVo deleteShopping(Integer sid, HttpSession session){
        ResultVo resultVo = shoppingService.deleteShopping(sid,session);
        return resultVo;
    }

    @RequestMapping("changeShoppingNum")
    public ResultVo changeShoppingNum(Integer sid,Integer flag,HttpSession session){
        ResultVo resultVo = shoppingService.changeShoppingNum(sid,flag,session);
        return resultVo;
    }
}
