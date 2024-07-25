package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.User;
import com.kh.service.UserService;

@RestController //리액트랑 연결하는 RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping //api 주소값 users
	public List<User> findAll() {
		return userService.findAll();
	}
	
	
}
