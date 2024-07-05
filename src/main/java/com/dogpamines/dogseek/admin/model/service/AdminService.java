package com.dogpamines.dogseek.admin.model.service;

import com.dogpamines.dogseek.admin.model.dao.AdminMapper;
import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    private final ProductsMapper productsMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final String REGIST_KEY = "regist";
    private final String VISITANT_KEY = "visitant";
    private final String PRODUCT_VISIT = "product";
    private final String BOARD_KEY = "board";

    @Autowired
    public AdminService(AdminMapper adminMapper, ProductsMapper productsMapper, RedisTemplate redisTemplate) {
        this.adminMapper = adminMapper;
        this.productsMapper = productsMapper;
        this.redisTemplate = redisTemplate;
    }

    public Map<String, Object> selectAllCounts() {

        List<CountsDTO> countsInDate = adminMapper.selectAllCountsInDate();
        countsInDate.add(adminMapper.selectRemainCountsInDate());

        List<CountsDTO> countsInMonth = adminMapper.selectCountsInMonth();
        countsInMonth.add(adminMapper.selectTotalCounts());

        List<ProductsDTO> productList = productsMapper.selectAllProducts();

        int productsSum = 0;
        int boardSum = 0;

        try {

            SetOperations<String, String> countVisit = redisTemplate.opsForSet();
            SetOperations<String, String> countRegist = redisTemplate.opsForSet();
            ValueOperations<String, String> countView = redisTemplate.opsForValue();
            ValueOperations<String, String> countBoard = redisTemplate.opsForValue();

            for (ProductsDTO product : productList) {

                int prodCode = product.getProdCode();

                String key = PRODUCT_VISIT + prodCode;

                Optional<String> tempView = Optional.ofNullable(countView.get(key));

                if (!tempView.isEmpty()) {

                    int prodView = product.getProdVisit();
                    int redisView = Integer.parseInt(countView.get(key));

                    product.setProdVisit(prodView + redisView);
                    productsSum += redisView;
                }
            }

            Optional<String> tempBoard = Optional.ofNullable(countBoard.get(BOARD_KEY));

            if (!tempBoard.isEmpty()) {

                boardSum = Integer.parseInt(countBoard.get(BOARD_KEY));
            }

            int countRegists = countsInDate.get(0).getCountsSignup();
            int tempRegists = Integer.parseInt(String.valueOf(countRegist.size(REGIST_KEY)));
            int updatedCountSignup = countRegists + tempRegists;

            int countVisits = countsInDate.get(0).getCountsSignin();
            int tempVisits = Integer.parseInt(String.valueOf(countVisit.size(VISITANT_KEY)));
            int updatedCountSignin = countVisits + tempVisits;

            int countProducts = countsInDate.get(0).getCountsProducts();
            int updatedCountProducts = countProducts + productsSum;

            int countBoards = countsInDate.get(0).getCountsBoards();
            int updatedCountBoards = countBoards + boardSum;

            countsInDate.get(0).setCountsSignup(updatedCountSignup);
            countsInDate.get(0).setCountsSignin(updatedCountSignin);
            countsInDate.get(0).setCountsProducts(updatedCountProducts);
            countsInDate.get(0).setCountsBoards(updatedCountBoards);
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(productList);

        Map<String, Object> result = new HashMap<>();
        result.put("Overview", countsInDate);
        result.put("Summary", countsInMonth);
        result.put("Popular", productList);

        return result;
    }
}
