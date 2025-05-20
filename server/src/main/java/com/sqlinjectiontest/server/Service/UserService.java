package com.sqlinjectiontest.server.Service;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import com.sqlinjectiontest.server.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class); 
    //로그인 모드 지정 상수
    public static final int LOGIN_MODE_NATIVE = 0;
    public static final int LOGIN_MODE_FILTERED = 1;
    public static final int LOGIN_MODE_PARAM_BINDING = 2;
    public static final int LOGIN_MODE_JPA = 3;
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** 로그인 함수
     * @param id id
     * @param password 비밀번호
     * @param loginMode 로그인 구현 방식을 지정(상수로 선언된 값 사용)
     *                  LOGIN_MODE_NATIVE: sql 삽입에 취약한 문자열 연결 방식 그대로 사용
     *                  LOGIN_MODE_FILTERED: 파라미터 필터링 후 문자열 연결 사용
     *                  LOGIN_MODE_PARAM_BINDING: 파라미터 바인딩을 이용(PreparedStatement)
     *                  LOGIN_MODE_JPA: JPA 이용
     * @return 로그인 성공여부
     */
    public boolean login(String id, String password, int loginMode) {
        List<UsersEntity> users = null;
        switch (loginMode) {
            case LOGIN_MODE_FILTERED:
                if(isSuspiciousCharacterPresent(id, password)) {
                    return false;
                }
            case LOGIN_MODE_NATIVE:
                users = userRepository.getUsersByIdAndPassword(id, password);
                break;
            case LOGIN_MODE_PARAM_BINDING:
                userRepository.getUsersByIdAndPasswordBinding(id, password);
                break;
            case LOGIN_MODE_JPA:
                UsersEntity user = userRepository.findById(id).orElse(null);
                if(user == null) {
                    log.error("invalid user id " + id);
                    return false;
                } else if (!password.equals(user.getPw())) {
                    log.error("invalid password " + password);
                    return false;
                }
                else {
                    return true;
                }
            default:
                log.error("Invalid login mode");
                throw new IllegalStateException("Invalid login mode");
        }
        
        if (users == null || users.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    /** 문자열 필터링 함수
     * 필터링 대상 문자(열): ' " \ ; --
     * @param inputs 검사할 문자열
     * @return 문제있는 문자(열) 존재여부
     */
    public boolean isSuspiciousCharacterPresent(String... inputs) {
        String regex = ".*[\"'\\\\;--].*";
        for (String input : inputs) {
            if (input.matches(regex)) {
                log.error("suspicious character detected");
                return true;
            }
        }
        return false;
    }
}
