package com.dogpamines.dogseek.products.model.service;

import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private ProductsMapper prouctsMapper;

    @Autowired
    public ProductsService(ProductsMapper productsMapper) {
        this.prouctsMapper = productsMapper;
    }

    public Object selectFindByCode(int prodCode) {
        return prouctsMapper.selectFindByCode(prodCode);
    }
}
