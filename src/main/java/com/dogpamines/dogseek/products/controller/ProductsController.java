package com.dogpamines.dogseek.products.controller;

import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import com.dogpamines.dogseek.products.model.service.ProductsService;
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

@RestController
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> selectAllProducts() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.selectAllProducts());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @GetMapping("/products/{prodCode}")
    public ResponseEntity<Map<String, Object>> selectFindByCode(@PathVariable int prodCode) {
        
         HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("product", productsService.selectFindByCode(prodCode));
          
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/products/comparison")
    public ResponseEntity<Map<String, Object>> comparisonProducts(@RequestParam int prodCode1, int prodCode2) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.productsComparison(prodCode1,prodCode2));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

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

    @GetMapping("/products/prodsearch")
    public ResponseEntity<Map<String, Object>> productSearch(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("products", productsService.productSearch(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> insertProduct(@RequestBody ProductsDTO product) {

        int newProdCode = productsService.getLastProdCode() + 1;
        product.setProdCode(newProdCode);

        productsService.insertProduct(product);

        return ResponseEntity
                .created(URI.create("/products/" + newProdCode))
                .build();
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody ProductsDTO product) {

        productsService.updateProduct(product);

        return ResponseEntity
                .created(URI.create("/products/" + product.getProdCode()))
                .build();
    }

    @DeleteMapping("/products/{prodCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable int prodCode) {

        productsService.deleteProduct(prodCode);

        return ResponseEntity.noContent().build();
    }
}
