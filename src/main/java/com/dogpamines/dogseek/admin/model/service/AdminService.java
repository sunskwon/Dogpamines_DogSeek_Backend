package com.dogpamines.dogseek.admin.model.service;

import com.dogpamines.dogseek.admin.model.dao.AdminMapper;
import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    private final ProductsMapper productsMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final String PRODUCT_VISIT = "product";

    @Autowired
    public AdminService(AdminMapper adminMapper, ProductsMapper productsMapper, RedisTemplate redisTemplate) {
        this.adminMapper = adminMapper;
        this.productsMapper = productsMapper;
        this.redisTemplate = redisTemplate;
    }

    public List<CountsDTO> selectAllCounts() {

        List<CountsDTO> counts = adminMapper.selectAllCounts();

        int prodCode = productsMapper.getLastProdCode();
        int productsSum = 0;

        try {

            ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

            for (int i = 1; i <= prodCode; i++) {

                String key = PRODUCT_VISIT + i;
                Optional<String> tempVisit = Optional.ofNullable(countVisit.get(key));

                if (!tempVisit.isEmpty()) {

                    productsSum += Integer.parseInt(countVisit.get(key));
                }

            }

            int countProducts = counts.get(0).getCountsProducts();
            int updatedCountProducts = countProducts + productsSum;

            counts.get(0).setCountsProducts(updatedCountProducts);
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counts;
    }
}
