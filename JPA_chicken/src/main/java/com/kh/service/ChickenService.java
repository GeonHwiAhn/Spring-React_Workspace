package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.Chicken;
import com.kh.repository.ChickenRepository;

@Service
public class ChickenService {
	
	@Autowired
	private ChickenRepository chickenRepository;
	
	//치킨테이블 모두보기 List 목록으로 전체보기 어떤 목록을 전체 볼거니?
	// List<Chicken>	목록<주제>
	public List<Chicken> getAllChickens() {
		return chickenRepository.findAll(); //find=찾기 All=모두
	}
	
	// 치킨 메뉴 추가하기
	public Chicken createChicken (Chicken chicken) {
		return chickenRepository.save(chicken); //치킨에 대해서 DTO에 작성된 컬럼들에 모두 삽입
	}
	
	//치킨 삭제하기
	public Chicken deleteChicken (Chicken chicken) {
		return deleteChicken(chicken);
	}
}
