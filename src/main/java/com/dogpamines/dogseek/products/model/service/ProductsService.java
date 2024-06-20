package com.dogpamines.dogseek.products.model.service;

import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductsService {

    private ProductsMapper productsMapper;

    @Autowired
    public ProductsService(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    public Object selectFindByCode(int prodCode) {
        return productsMapper.selectFindByCode(prodCode);
    }

    public List<ProductsDTO> selectAllProducts() {
        return productsMapper.selectAllProducts();
    }

    public List<ProductsDTO> productsComparison(int prodCode1, int prodCode2) {
        return productsMapper.productsComparison(prodCode1, prodCode2);
    }

    public List<ProductsDTO> searchProducts(String value, String prodRecom, String prodAge, String prodCook, String prodSize, String prodEffi, Integer prodPrice) {
        return productsMapper.searchProducts(value, prodRecom, prodAge, prodCook, prodSize, prodEffi, prodPrice);
    }

    public List<ProductsDTO> productSearch(String type, String input) {
        return productsMapper.productSearch(type, input);
    }

    public int getLastProdCode() {
        return productsMapper.getLastProdCode();
    }

    public void insertProduct(ProductsDTO product) {
        productsMapper.insertProduct(product);
    }
}
