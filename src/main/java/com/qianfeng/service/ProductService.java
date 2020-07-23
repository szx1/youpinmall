package com.qianfeng.service;

import com.qianfeng.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> getListByTypeId(Integer tid);

    Product getProductById(Integer bid);
}
