package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.service.APIService;

/*
 * 공공데이터 API 활용 컨트롤러
 *  
 */

@RestController
public class APIController {

	@Autowired
	private APIService apiSerivce;
	
	@GetMapping("/api/dataService")
	public String dataApi() {
		String endpoint = "/끝나는주소";
		return apiSerivce.getApiData(endpoint);
		
	}
}
