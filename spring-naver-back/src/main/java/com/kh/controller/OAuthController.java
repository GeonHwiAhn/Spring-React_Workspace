package com.kh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mysql.cj.Session;

//import javax.		; javax는 구버전
//import jakarta.	; jakarta가 신버전 import를 할 때 javax로 하게되면 에러발생
import jakarta.servlet.http.HttpSession;

/*
 * 24-07-30 NaverLogin을 한 후 로그인한 내용을 React에서 볼 수 있도록
 * NaverLoginController.java 파일을 수정
 * NaverLoginController.java 주소 (api url) 충돌을 막기 위해
 * @RequsetMapping("/api")를 제거함
 */




@RestController
//@RequestMapping("/api")
public class OAuthController {
	/*
	 @Value = application.properties나 config.properties에 작성한
	  키이름을 가져오고 키에 담긴 값을 가져오는 어노테이션
	 */
	
	// application.properties naver.client-id = Y36UTtDTxFvb3BZs1v_z
	@Value("${naver.client-id}")
	private String clientId; 	 //clientId = Y36UTtDTxFvb3BZs1v_z
	
	// application.properties naver.client-secret = O_69J6LTa1
	@Value("${naver.client-secret}")
	private String clientSecret; //clientSecret = O_69J6LTa1
	
	// application.properties naver.redirect-uri = http://localhost:9010/naverLogin
	@Value("${naver.redirect-uri}")
	private String redirectUri;  //redirectUri = http://localhost:9010/naverLogin
	
	// application.properties naver.state = RANDOM_STATE
	@Value("${naver.state}")
	private String state;		 // state = RANDOM_STATE
	
	
	/*
	 app.get('/naverLogin', function (req, res) {
  		api_url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + client_id + '&redirect_uri=' + redirectURI + '&state=' + state;
   		res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
   		res.end("<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>");
 	});
	 */
	
	@GetMapping("/naverLogin") //localhost:9010/naverLogin
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";

	}
	
	/*
	 url에 {}=변수명표시가 없으면 @RequestParam 이나 @RequestBody
	 url에 {}=변수명표시가 있으면 @PathVariable {}안에있는 변수명에 값을 집어넣는다.
	 */
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state, HttpSession session) {
		// 네이버에서 로그인을 성공하고 성공했을 때 받는 도장
		// 1. client-id    = 어디회사에 들어왔는지 
		// 2. clientSecret = 회사 들어오기 위한 비밀번호
		// 3. redirectUri  = 들어오기 위한 심사를 무사히 완료했으면 내보내는 위치로 전달
		// 4. code 		   = 네이버로부터 무사히 들어왔다는 인증코드 받음
		// 5. state		   = CSRF 공격을 방지하기 위해 사용
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirectUri=" + redirectUri + "&code=" + code + "&state=" + state;
	    //RestTemplate = HTTP 메서드 GET POST PUT DELETE 통해서
		// 데이터를 JSON 형식으로 데이터를 처리
		RestTemplate restTemplate = new RestTemplate();
		// api_url 주소로 응답받은 결과를 String = 문자로 가져와서 사용하겠다.
		
		//String, Object 앞의 값은 키이름이기 때문에 String
		//키이름에 담긴 값은 String이라는 확정을 지울 수 없으므로 Object 가져옴
		Map<String, Object> 응답결과 = restTemplate.getForObject(api_url, Map.class);
		
		System.out.println("Token response : " + 응답결과);
		
		//token 인증받은 값을 가져오는데 Bearer access_token 사용
		//가져온 토큰 데이터를 문자열로 변환해서 글자처럼 사용
		String accessToken = (String) 응답결과.get("access_token"); 
		//네이버 개발자문서에 보면 access_token으로 로그인 허용된 값을 가져가라 작성
		
		String 유저정보가담긴Url = "https://openapi.naver.com/v1/nid/me";
		//import org.springframework.http.HttpHeaders;
		HttpHeaders headers = new HttpHeaders();
		//네이버개발자에서 제공한 작성양식
		headers.set("Authorization", "Bearer" + accessToken);
		
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		// HttpEntity = 응답, 요청 모두 있음 상세한기능X
		//ResponseEntity RequestEntity = 각자상세기능보유
		
		ResponseEntity<Map> userInfoRes = restTemplate.exchange(유저정보가담긴Url, HttpMethod.GET, entity, Map.class);
		
		Map<String, Object> userInfo = userInfoRes.getBody();
		session.setAttribute("userInfo", userInfo); //session에 로그인정보를 담겠다.
		
		
		
		
		/*
		 HttpHeaders에 인증에 대한 값을 Bearer 가져오기
		 Bearer : 인증을 위해 서버에서 제공되는 보안 토큰
		 주로 사용자가 인증을 받고나서 API 요청을 할 때 사용
		 
		 예를 들어, 네이버에 로그인을 하고 나면 Naver 사용자에게 로그인됐다는 토큰 발급
		 추후 네이버에 로그인이 된 기록을 가지고 어떤 요청을 하면
		 요청을 할 때 헤더에 Authorization:Bearer{} " 작성하고 요청을 해야함
		 
		 Bearer = 소지자, 보유한사람
		 Authorization = 권한 부여
		 Authorization	:	Bearer		   {					}
		 권한부여			:   권한을 가지고있는사람 {"권한을 가지고있는 번호"	}	
		 */
		return "redirect";
	}
	
	//가져온 네이버 정보를 리액트로 전달할 GetMapping
	@GetMapping("/userInfo")
	public Map<String, Object> userInfo(HttpSession session) {
										//httpSession을 Map으로 형변환
		Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("userInfo");
		return userInfo;
	}
	
	
}
