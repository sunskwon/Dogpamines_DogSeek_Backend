package com.dogpamines.dogseek.main.model.service;

import com.dogpamines.dogseek.main.model.dao.MainMapper;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MainService {

    private MainMapper mainMapper;

    @Autowired
    public MainService(MainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    public List<ProductsDTO> selectLastProds() {

        return mainMapper.selectLastProds();
    }

}
