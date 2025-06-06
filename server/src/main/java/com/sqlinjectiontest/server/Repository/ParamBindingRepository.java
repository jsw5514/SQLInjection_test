package com.sqlinjectiontest.server.Repository;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParamBindingRepository {
    @Query(value = "SELECT * FROM users WHERE id = :id AND pw = :password", nativeQuery = true)
    List<Object[]> getUsersByIdAndPasswordBinding(@Param("id") String id,@Param("password") String password);
}
