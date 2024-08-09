package com.kh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostMapper postMapper;
	
	//import org.springframework.beans.factory.annotation.Value;
	@Value("${file.upload-dir}") //application.properties에서 설정이름 가져와
	private String uploadDir;
	//file.upload-dir=C:/Users/user1/Desktop/saveImage 경로 넣어서 사용
	
	@Override
	public List<Post> findAll() {
		
		return postMapper.findAll();
	}
	
	@Override
	public void insertPost(Post post) {
		postMapper.insertPost(post);
	}
	
	//Service에 작성 후 ctrl+spaceBar
	@Override
	public void uploadImages(MultipartFile[] files, String title, String content) {
		// 1. 바탕화면에 이미지 저장하고 불러올 폴더가 존재하는지 확인하고 없으면 폴더 생성
		File uploadDirFile = new File(uploadDir);
		// 만약에 폴더가 존재할 경우
		// uploadDirFile.exists() = 폴더가 존재한다.
		// !uploadDirFile.exists() = 폴더가 존재하지 않는다.
		if(!uploadDirFile.exists()) {
			System.out.println("폴더가 존재하지 않아 폴더를 생성합니다.");
			if (!uploadDirFile.mkdirs()) {
				throw new RuntimeException("폴더 생성 실패하였습니다.");
				//mkdir = 폴더1개 mkdirs = 하위폴더 모두 생성
			}
		} 
		// UUID 이미지 이름 저장 중복없이 저장할 수 있도록 설정
		List<String> fileNames = null;
		//파일명이 1개일 수 있고, 여러이름이 들어올 수 있기 때문에
		// fileNames = 파일이름들 리스트로 글자목록이 작성
		
		try {
			// MultipartFile[] files = array배열로 파일들 담기
			// List.of(files) files  = 파일들 배열을 리스트(목록)로 변환
			// .stream() = 리스트나 배열과 같은데이터를 하나씩 처리하는 기능
			// .collect(Collectors.toList()); stream으로 가져온 이미지 데이터를 리스트로 정렬
			
			// 이미지들 이미지를 한개씩 담아오기 map -> 이미지를 하나씩 가져올 때 stream을 이용해서 가져오기
			// 스트림을 이용해서 가져온 이미지를 collect를 이용해서 리스트로 모음
			// 한번 더 리스트로 목록 변환
			fileNames = List.of(files).stream().map(file -> {
				// 파일을 폴더에 저장 폴더에 파일을 저장할 때 이미지에 랜덤으로 이름을 부여해서 저장
				// UUID를 이용해서 저장 = 파일이름이 겹치지않도록 랜덤으로 이름 생성
				String fileName1 = UUID.randomUUID().toString();
				// 랜덤으로작성한 이름 뒤에 원본이름을 붙이고싶을 때
				// + file.getOriginalFilename(); 붙여줌
				String fileName2 = UUID.randomUUID().toString() + file.getOriginalFilename();
				//랜덤으로 작성한이름 _ 원본이름, _로 랜덤이름과 원본이름 구분짓고 싶을 때
				String fileName3 = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				
				// 폴더 안에 이미지를 저장하기 완성
				// File.separator = window 
				File df = new File(uploadDir + File.separator + fileName3);
				
				try {
					file.transferTo(df);
				} catch (IOException e) {
					throw new RuntimeException("파일 업로드 실패", e);
				}
				return fileName3;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setImageUrl(String.join(",", fileNames));
		insertPost(post);
		

		
	}
	/*
	@Override
	public void updatePost(Post post) {
		// insertPost 그대로 쓰면 됨
		// 바탕화면에 이미지가 저장된 폴더에서 기존이미지를 삭제
		
	}
	*/
}