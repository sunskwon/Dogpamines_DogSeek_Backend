package com.dogpamines.dogseek.curation.model.service;

import com.dogpamines.dogseek.curation.model.dao.CurationMapper;
import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurationService {

    private CurationMapper curationMapper;

    @Autowired
    public CurationService(CurationMapper curationMapper) {this.curationMapper = curationMapper;}
    public Integer lastCuration() {
        return curationMapper.lastCuration();
    }

    public void insertCuration(CurationDTO curationDTO) {
        curationMapper.insertCuration(curationDTO);
    }
}
