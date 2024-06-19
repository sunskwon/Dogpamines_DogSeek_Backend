package com.dogpamines.dogseek.products.model.dao;

import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductsMapper {

    List<ProductsDTO> selectAllProducts();
    Object selectFindByCode(int prodCode);
    List<ProductsDTO> productsComparison(int prodCode1, int prodCode2);
    List<ProductsDTO> searchProducts(String value, String prodRecom, String prodAge, String prodCook, String prodSize, String prodEffi, Integer prodPrice);

    List<ProductsDTO> productSearch(String type, String input);
}
