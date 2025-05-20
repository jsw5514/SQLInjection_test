package com.sqlinjectiontest.server.Repository.Impl;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import com.sqlinjectiontest.server.Repository.NativeSQLRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class NativeSQLRepositoryImpl implements NativeSQLRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    /** SQL 삽입에 취약한 로그인용 함수
     * @param id id
     * @param password 비밀번호
     * @return 입력정보와 일치하는 유저 리스트
     */
    @Override
    public List<UsersEntity> getUsersByIdAndPassword(String id, String password) {
        String sql = "select * from users where id = '" + id + "' and pw = '" + password + "'";
        List<UsersEntity> result = entityManager.createNativeQuery(sql).getResultList();
        return result;
    }
}
