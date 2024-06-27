package com.dogpamines.dogseek.dict.model.service;

import com.dogpamines.dogseek.dict.model.dao.DictMapper;
import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictService {

    private DictMapper dictMapper;

    @Autowired
    public DictService(DictMapper dictMapper){this.dictMapper = dictMapper;}

    public List<DictDTO> selectAllDog(){ return dictMapper.selectAllDog();}

    public DictDTO selectByCode(String dogName) { return dictMapper.selectByCode(dogName);}

    public List<DictDTO> searchDog(String dogName) { return dictMapper.searchDog(dogName);
    }

    public List<DictDTO> dictSearch(String type, String input) {

        return dictMapper.dictSearch(type, input);
    }

    public int getLastDogCode() {

        return dictMapper.getLastDogCode();
    }

    public void deleteDict(int dogCode) {

        dictMapper.deleteDict(dogCode);
    }

    public void insertDict(DictDTO dict) {

        dictMapper.insertDict(dict);
    }

    public void updateDict(DictDTO dict) {

        dictMapper.updateDict(dict);
    }
}
