package com.sqlinjectiontest.server.DTO;

import com.sqlinjectiontest.server.Entity.UsersEntity;

public class User {
    public String id;
    public String pw;
    
    public static User fromEntity(UsersEntity entity) {
        User user = new User();
        user.id = entity.getId();
        user.pw = entity.getPw();
        return user;
    }
}
