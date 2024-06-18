package com.dogpamines.dogseek.dict.model.service;

import com.dogpamines.dogseek.dict.model.dao.DictMapper;
import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictService {

    private DictMapper dictMapper;

    @Autowired
    public DictService(DictMapper dictMapper){this.dictMapper = dictMapper;}

    public List<DictDTO> selectAllDog(){ return dictMapper.selectAllDog();}

    public DictDTO selectByCode(int dogCode) { return dictMapper.selectByCode(dogCode);}
}
