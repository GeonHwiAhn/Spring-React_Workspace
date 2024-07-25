package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

//서비스 목록 리스트 여기는 목록만 작성해주고 imple 오버라이드 해서 각 맞게 재사용
@Service //서비스 기능을 상세하게 작성해주는 공간
public class UserServiceImpl implements UserService{
	
	@Autowired 
	UserMapper userMapper; 
	
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
}
