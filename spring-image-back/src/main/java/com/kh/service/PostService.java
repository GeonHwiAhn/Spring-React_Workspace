package com.kh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.dto.Post;

public interface PostService {

	List<Post> findAll();
}
