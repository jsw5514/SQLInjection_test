package com.sqlinjectiontest.server.Controller;

import com.sqlinjectiontest.server.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String loginUserName = userService.login(request.get("id"), request.get("pw"), UserService.LOGIN_MODE_NATIVE);
        if(loginUserName != null) {
            return ResponseEntity.ok().header("name", loginUserName).body("로그인 성공");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }
}
