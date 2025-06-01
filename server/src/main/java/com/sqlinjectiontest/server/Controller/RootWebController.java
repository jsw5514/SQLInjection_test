package com.sqlinjectiontest.server.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootWebController {
    @GetMapping("/")
    public String home() {
        return "forward:/login.html"; // 루트 URL에서 login.html을 직접 반환
    }
}
