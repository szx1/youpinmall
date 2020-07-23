package com.qianfeng.service;

import com.qianfeng.dao.ProductMapper;
import com.qianfeng.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getListByTypeId(Integer tid) {
        List<Product> productList = productMapper.getListByTypeId(tid);
        return productList;
    }

    @Override
    public Product getProductById(Integer bid) {
        return productMapper.selectByPrimaryKey(bid);
    }
}
