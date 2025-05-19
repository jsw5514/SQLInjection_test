package com.sqlinjectiontest.server.Repository;

import com.sqlinjectiontest.server.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, String>, NativeSQLRepository {
}
