package com.sqlinjectiontest.server.Service;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import com.sqlinjectiontest.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** 네이티브 sql 쿼리를 이용해 로그인하는 함수(sql 삽입에 취약)
     * @param id id
     * @param password 비밀번호
     * @return 로그인 성공여부
     */
    public boolean loginNative(String id, String password) {
        List<UsersEntity> users = userRepository.getUsersByIdAndPassword(id, password);
        if (users.size() == 0) {
            return false;
        }
        else return true;
    }
}
