package com.dogpamines.dogseek.mypage.model.service;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.mypage.model.dao.MyPageMapper;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
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

    public List<UserDTO> selectUserDetail(int userCode) {

        return myPageMapper.selectUserDetail(userCode);
    }

    public void updateUser(UserDTO updateInfo) {

        myPageMapper.updateUser(updateInfo);
    }

    public String findUserAuth(int userCode) {

        return myPageMapper.findUserAuth(userCode);
    }

    public void deleteUser(String userAuth, int userCode) {

        myPageMapper.deleteUser(userAuth, userCode);
    }

    public List<HistoryDTO> selectMyCurationResult(int curationCode) {

        return myPageMapper.selectMyCurationResult(curationCode);
    }


    public List<UserDTO> checkInfo() {
        return myPageMapper.checkInfo();
    }
}
