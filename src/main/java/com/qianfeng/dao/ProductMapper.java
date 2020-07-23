package com.qianfeng.dao;

import com.qianfeng.pojo.Product;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    Product selectByPrimaryKey(Integer productId);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    List<Product> getListByTypeId(Integer tid);
}