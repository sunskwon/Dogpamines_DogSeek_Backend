package com.dogpamines.dogseek.admin.model.service;

import com.dogpamines.dogseek.admin.model.dao.AdminMapper;
import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminMapper adminMapper;

    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public List<CountsDTO> selectAllCounts() {
        return adminMapper.selectAllCounts();
    }
}
