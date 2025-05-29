package com.sqlinjectiontest.server.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class UsersEntity {
  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "pw")
  private String pw;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "user_role")
  private String userRole;
  
  public static UsersEntity fromObjectArray(Object[] row){
    UsersEntity user = new UsersEntity();
    user.setId((String) row[0]);
    user.setPw((String) row[1]);
    user.setUserName((String) row[2]);
    user.setUserRole((String) row[3]);
    return user;
  }
  
  public static List<UsersEntity> fromListOfObjectArray(List<Object[]> rows){
    List<UsersEntity> users = new ArrayList<>();
    for(Object[] row : rows){
      users.add(fromObjectArray(row));
    }
    return users;
  }
}
