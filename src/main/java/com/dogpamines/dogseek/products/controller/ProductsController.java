package com.dogpamines.dogseek.products.controller;

import com.dogpamines.dogseek.common.model.service.RedisService;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import com.dogpamines.dogseek.products.model.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Tag(name = "PROD(사료) Controller")
@RestController
public class ProductsController {

    private final ProductsService productsService;
    private final RedisService redisService;

    private final String PRODUCTS_KEY = "product";

    @Autowired
    public ProductsController(ProductsService productsService, RedisService redisService) {
        this.productsService = productsService;
        this.redisService = redisService;
    }
    @Operation(summary = "사료 전체 조회" , description = "모든 사료의 정보를 조회한다.")
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> selectAllProducts() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.selectAllProducts());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 사료 코드로 조회" , description = "관리자는 사료를 코드로 조회한다.")
    @GetMapping("/products/{prodCode}")
    public ResponseEntity<Map<String, Object>> selectFindByCode(@PathVariable int prodCode, @RequestHeader Map<String, String> req) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        String identifier = "";

        try {

            System.out.println(req.get("identifier"));

            if (req.get("identifier") == "") {

                String uuidString = UUID.randomUUID().toString();
                headers.set("Identifier", uuidString);
                identifier = uuidString;
            } else {

                identifier = req.get("identifier");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 상세 정보 호출시 redis에 조회수 추가
        String key = PRODUCTS_KEY + prodCode;

        redisService.addCount(key, identifier);

        Map<String, Object> result = new HashMap<>();
        result.put("product", productsService.selectFindByCode(prodCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "동일 사료 용량별 조회" , description = "동일한 사료의 용량이 다를 경우 안내하기 위해 사용한다.")
    @GetMapping("/products/volume")
    public ResponseEntity<Map<String, Object>> findByName(@RequestParam String prodName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("product", productsService.findByName(prodName));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @Operation(summary = "사료 비교" , description = "사용자는 두가지의 사료를 비교할 수 있다.")
    @GetMapping("/products/comparison")
    public ResponseEntity<Map<String, Object>> comparisonProducts(@RequestParam int prodCode1, int prodCode2) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.productsComparison(prodCode1, prodCode2));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "사료 검색" , description = "사용자는 기호에 맞는 사료를 필터를 거쳐 조회할 수 있다.")
    @GetMapping("/products/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String value, String prodRecom,
                                                              String prodAge, String prodCook, String prodSize,
                                                              String prodEffi, Integer prodPrice) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.searchProducts(value, prodRecom, prodAge, prodCook, prodSize, prodEffi, prodPrice));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "조회수가 많은 사료" , description = "사용자는 메인화면에서 조회수가 가장 높은 사료 순으로 조회할 수 있다.")
    @GetMapping("/products/mostProducts")
    public ResponseEntity<Map<String, Object>> mostProducts() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.mostProducts());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 사료 조회" , description = "관리자는 사료의 이름과 제조사로 사료를 검색할 수 있다.")
    @GetMapping("/products/prodsearch")
    public ResponseEntity<Map<String, Object>> productSearch(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.productSearch(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "사료 정보 등록" , description = "관리자는 새로운 사료를 등록할 수 있다.")
    @PostMapping("/products")
    public ResponseEntity<?> insertProduct(@RequestBody ProductsDTO product) {

        int newProdCode = productsService.getLastProdCode() + 1;
        product.setProdCode(newProdCode);

        productsService.insertProduct(product);

        return ResponseEntity
                .created(URI.create("/products/" + newProdCode))
                .build();
    }
    @Operation(summary = "사료 정보 수정" , description = "관리자는 사료의 정보를 수정할 수 있다.")
    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody ProductsDTO product) {

        productsService.updateProduct(product);

        return ResponseEntity
                .created(URI.create("/products/" + product.getProdCode()))
                .build();
    }
    @Operation(summary = "사료 정보 삭제" , description = "관리자는 사료의 정보를 삭제할 수 있다.")
    @DeleteMapping("/products/{prodCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable int prodCode) {

        productsService.deleteProduct(prodCode);

        return ResponseEntity.noContent().build();
    }
}
