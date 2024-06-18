package com.dogpamines.dogseek.products.model.dao;

import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductsMapper {
    List<ProductsDTO> selectAllProducts();
}
