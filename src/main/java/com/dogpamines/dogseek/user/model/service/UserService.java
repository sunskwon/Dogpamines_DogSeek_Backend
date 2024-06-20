package com.dogpamines.dogseek.user.model.service;

import com.dogpamines.dogseek.common.UserRole;
import com.dogpamines.dogseek.user.model.dao.UserMapper;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO findByUsername(String username) {

        UserDTO login = userMapper.findByUsername(username);

        if (!Objects.isNull(login)) {
            return login;
        } else {
            return null;
        }
    }
}
