package com.dogpamines.dogseek.common.model.service;

import com.dogpamines.dogseek.common.model.dao.ScheduledMapper;
import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@EnableScheduling
public class ScheduledService {

    private final RedisTemplate redisTemplate;
    private final ScheduledMapper scheduledMapper;
    private final ProductsMapper productsMapper;

    private final String REGIST_KEY = "regist";
    private final String VISITANT_KEY = "visitant";
    private final String PRODUCT_VISIT = "product";

    public ScheduledService(RedisTemplate redisTemplate, ScheduledMapper scheduledMapper, ProductsMapper productsMapper) {
        this.redisTemplate = redisTemplate;
        this.scheduledMapper = scheduledMapper;
        this.productsMapper = productsMapper;
    }

    @Scheduled(cron = "5 0 0 * * ?")
    @Transactional
    public void createNewRow() {

        System.out.println("createNewRow() 실행...");

        String date = "yesterday";

        int signupSum = 0;
        int signinSum = 0;
        int productsSum = 0;
        int prodCode = productsMapper.getLastProdCode();

        try {

            SetOperations<String, String> countRegist = redisTemplate.opsForSet();
            SetOperations<String, String> countVisit = redisTemplate.opsForSet();
            ValueOperations<String, String> countView = redisTemplate.opsForValue();

            for (int i = 1; i <= prodCode; i++) {

                String key = PRODUCT_VISIT + i;
                Optional<String> tempVisit = Optional.ofNullable(countView.get(key));

                if (!tempVisit.isEmpty()) {

                    ProductsDTO product = productsMapper.selectFindByCode(i);

                    int visit = product.getProdVisit();
                    int updatedVisit = visit + Integer.parseInt(countView.get(key));

                    product.setProdVisit(updatedVisit);

                    productsMapper.updateProduct(product);

                    productsSum += Integer.parseInt(countView.get(key));

                    redisTemplate.delete(key);
                }
            }

            signupSum = Integer.parseInt(String.valueOf(countRegist.size(REGIST_KEY)));
            signinSum = Integer.parseInt(String.valueOf(countVisit.size("visitant")));

            redisTemplate.delete(REGIST_KEY);
            redisTemplate.delete(VISITANT_KEY);
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountsDTO prevCounts = scheduledMapper.selectCounts(date);

        int signup = prevCounts.getCountsSignup();
        int signin = prevCounts.getCountsSignin();
        int products = prevCounts.getCountsProducts();

        prevCounts.setCountsSignup(signup + signupSum);
        prevCounts.setCountsSignin(signin + signinSum);
        prevCounts.setCountsProducts(products + productsSum);

        scheduledMapper.updateCounts(prevCounts);
        scheduledMapper.createNewRow();
    }

    @Scheduled(cron = "5 0 1/1 * * ?")
    @Transactional
    public void updateCounts() {
        System.out.println("updateCounts() 실행...");
        String date = "today";

        int signupSum = 0;
        int signinSum = 0;
        int productsSum = 0;
        int prodCode = productsMapper.getLastProdCode();

        try {

            SetOperations<String, String> countRegist = redisTemplate.opsForSet();
            SetOperations<String, String> countVisit = redisTemplate.opsForSet();
            ValueOperations<String, String> countView = redisTemplate.opsForValue();

            for (int i = 1; i <= prodCode; i++) {

                String key = PRODUCT_VISIT + i;
                Optional<String> tempVisit = Optional.ofNullable(countView.get(key));

                if (!tempVisit.isEmpty()) {

                    ProductsDTO product = productsMapper.selectFindByCode(i);

                    int visit = product.getProdVisit();
                    int updatedVisit = visit + Integer.parseInt(countView.get(key));

                    product.setProdVisit(updatedVisit);

                    productsMapper.updateProduct(product);

                    productsSum += Integer.parseInt(countView.get(key));

                    redisTemplate.delete(key);
                }
            }

            signupSum = Integer.parseInt(String.valueOf(countRegist.size(REGIST_KEY)));
            signinSum = Integer.parseInt(String.valueOf(countVisit.size(VISITANT_KEY)));

            redisTemplate.delete(REGIST_KEY);
            redisTemplate.delete(VISITANT_KEY);
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountsDTO prevCounts = scheduledMapper.selectCounts(date);

        int signup = prevCounts.getCountsSignup();
        int signin = prevCounts.getCountsSignin();
        int products = prevCounts.getCountsProducts();

        prevCounts.setCountsSignup(signup + signupSum);
        prevCounts.setCountsSignin(signin + signinSum);
        prevCounts.setCountsProducts(products + productsSum);

        scheduledMapper.updateCounts(prevCounts);
    }
}
