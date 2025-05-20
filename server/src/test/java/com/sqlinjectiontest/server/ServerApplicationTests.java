package com.sqlinjectiontest.server;

import com.sqlinjectiontest.server.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServerApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	void loginTest() {
		//test nomal login
		doNomalLogin("admin","adminpass");
		
		//test SQL injection
		doSQLInjection("admin';-- ","asdf");
	}

	void doNomalLogin(String id, String password) {
		boolean result;
		result = userService.login(id,password,UserService.LOGIN_MODE_NATIVE);
		assertTrue(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_FILTERED);
		assertTrue(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_PARAM_BINDING);
		assertTrue(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_JPA);
		assertTrue(result);
	}

	void doSQLInjection(String id, String password) {
		boolean result;
		result = userService.login(id,password,UserService.LOGIN_MODE_NATIVE);
		assertTrue(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_FILTERED);
		assertFalse(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_PARAM_BINDING);
		assertFalse(result);
		result = userService.login(id, password, UserService.LOGIN_MODE_JPA);
		assertFalse(result);
	}

}
