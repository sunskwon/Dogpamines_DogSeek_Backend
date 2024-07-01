package com.dogpamines.dogseek.products.model.service;

import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.List;
import java.util.Optional;


@Service
public class ProductsService {

    private final ProductsMapper productsMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final String PRODUCT_VISIT = "product";

    @Autowired
    public ProductsService(ProductsMapper productsMapper, RedisTemplate redisTemplate) {
        this.productsMapper = productsMapper;
        this.redisTemplate = redisTemplate;
    }

    public ProductsDTO selectFindByCode(int prodCode) {

        ProductsDTO product = productsMapper.selectFindByCode(prodCode);

        try {

            String key = PRODUCT_VISIT + prodCode;
            ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

            int count = Integer.parseInt(countVisit.get(key));
            int prevVisit = product.getProdVisit();
            int updatedVisit = count + prevVisit;

            product.setProdVisit(updatedVisit);
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<ProductsDTO> selectAllProducts() {

        List<ProductsDTO> products = productsMapper.selectAllProducts();

        for (ProductsDTO product : products) {

            try {

                int prodCode = product.getProdCode();
                String key = PRODUCT_VISIT + prodCode;
                ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

                Optional<String> tempVisit = Optional.ofNullable(countVisit.get(key));

                if (!tempVisit.isEmpty()) {

                    int count = Integer.parseInt(countVisit.get(key));
                    int prevVisit = product.getProdVisit();
                    int updatedVisit = count + prevVisit;

                    product.setProdVisit(updatedVisit);
                }
            } catch (RedisConnectionFailureException e) {
                System.out.println("redis와 연결되지 않음");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    public List<ProductsDTO> productsComparison(int prodCode1, int prodCode2) {
        return productsMapper.productsComparison(prodCode1, prodCode2);
    }

    public List<ProductsDTO> searchProducts(String value, String prodRecom, String prodAge, String prodCook, String prodSize, String prodEffi, Integer prodPrice) {
        return productsMapper.searchProducts(value, prodRecom, prodAge, prodCook, prodSize, prodEffi, prodPrice);
    }

    public List<ProductsDTO> mostProducts() {
        return productsMapper.mostProducts();
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

    public void updateProduct(ProductsDTO product) {
        productsMapper.updateProduct(product);
    }

    public void deleteProduct(int prodCode) {
        productsMapper.deleteProduct(prodCode);
    }
}
