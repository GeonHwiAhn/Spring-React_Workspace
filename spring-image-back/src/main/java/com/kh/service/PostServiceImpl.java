package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostMapper mapper;
	
	
}
