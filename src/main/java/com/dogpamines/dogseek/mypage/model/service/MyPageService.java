package com.dogpamines.dogseek.mypage.model.service;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.mypage.model.dao.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private MyPageMapper myPageMapper;

    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    public List<CurationDTO> findAllCuration() {

        return myPageMapper.findAllCuration();
    }

    public List<CurationDTO> userCurationList(int userCode) {

        return myPageMapper.userCurationList(userCode);
    }

    public List<CurationDTO> selectUserDogCurationList(int userCode, String curationName) {

        return myPageMapper.selectUserDogCurationList(userCode, curationName);
    }
}
