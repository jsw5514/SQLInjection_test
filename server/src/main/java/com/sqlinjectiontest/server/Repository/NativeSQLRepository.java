package com.sqlinjectiontest.server.Repository;

import com.sqlinjectiontest.server.Entity.UsersEntity;

import java.util.List;

public interface NativeSQLRepository {
    List<UsersEntity> getUsersByIdAndPassword(String id, String password);
}
