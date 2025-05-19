package com.sqlinjectiontest.server.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
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
}
