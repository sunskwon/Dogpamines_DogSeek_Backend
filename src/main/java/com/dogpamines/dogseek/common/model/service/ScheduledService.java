package com.dogpamines.dogseek.common.model.service;

import com.dogpamines.dogseek.common.model.dao.ScheduledMapper;
import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import com.dogpamines.dogseek.products.model.dao.ProductsMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final String PRODUCT_VISIT = "product";

    public ScheduledService(RedisTemplate redisTemplate, ScheduledMapper scheduledMapper, ProductsMapper productsMapper) {
        this.redisTemplate = redisTemplate;
        this.scheduledMapper = scheduledMapper;
        this.productsMapper = productsMapper;
    }

    @Scheduled(cron = "5 0 0 * * ?")
    @Transactional
    public void createNewRow() {

        String date = "yesterday";

        int prodCode = productsMapper.getLastProdCode();
        int productsSum = 0;

        try {

            ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

            for (int i = 1; i <= prodCode; i++) {

                String key = PRODUCT_VISIT + i;
                Optional<String> tempVisit = Optional.ofNullable(countVisit.get(key));

                if (!tempVisit.isEmpty()) {

                    ProductsDTO product = productsMapper.selectFindByCode(i);

                    int visit = product.getProdVisit();
                    int updatedVisit = visit + Integer.parseInt(countVisit.get(key));

                    product.setProdVisit(updatedVisit);

                    productsMapper.updateProduct(product);

                    productsSum += Integer.parseInt(countVisit.get(key));

                    redisTemplate.delete(key);
                }
            }
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountsDTO prevCounts = scheduledMapper.selectCounts(date);

        int products = prevCounts.getCountsProducts();
        prevCounts.setCountsProducts(products + productsSum);

        scheduledMapper.updateCounts(prevCounts);
        scheduledMapper.createNewRow();
    }

    @Scheduled(cron = "5 0 1/1 * * ?")
    @Transactional
    public void updateCounts() {
        System.out.println("updateCounts() 실행...");
        String date = "today";

        int prodCode = productsMapper.getLastProdCode();
        int productsSum = 0;

        try {

            ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

            for (int i = 1; i <= prodCode; i++) {

                String key = PRODUCT_VISIT + i;
                Optional<String> tempVisit = Optional.ofNullable(countVisit.get(key));

                if (!tempVisit.isEmpty()) {

                    ProductsDTO product = productsMapper.selectFindByCode(i);

                    int visit = product.getProdVisit();
                    int updatedVisit = visit + Integer.parseInt(countVisit.get(key));

                    product.setProdVisit(updatedVisit);

                    productsMapper.updateProduct(product);

                    productsSum += Integer.parseInt(countVisit.get(key));

                    redisTemplate.delete(key);
                }
            }
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountsDTO prevCounts = scheduledMapper.selectCounts(date);

        int products = prevCounts.getCountsProducts();
        prevCounts.setCountsProducts(products + productsSum);

        scheduledMapper.updateCounts(prevCounts);
    }
}
