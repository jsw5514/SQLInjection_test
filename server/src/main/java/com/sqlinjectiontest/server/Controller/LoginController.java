package com.sqlinjectiontest.server.Controller;

import com.sqlinjectiontest.server.Service.UserService;
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
        boolean loginSuccessed = userService.login(request.get("id"), request.get("pw"), UserService.LOGIN_MODE_NATIVE);
        if(loginSuccessed) {
            return ResponseEntity.ok("Login successful");
        }
        else {
            return ResponseEntity.ok("Login failed");
        }
    }
}
