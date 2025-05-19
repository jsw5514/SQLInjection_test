package com.sqlinjectiontest.server.Repository;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParamBindingRepository {
    @Query(value = "SELECT * FROM users WHERE id = :id AND pw = :password", nativeQuery = true)
    List<UsersEntity> getUsersByIdAndPasswordBinding(String id, String password);
}
