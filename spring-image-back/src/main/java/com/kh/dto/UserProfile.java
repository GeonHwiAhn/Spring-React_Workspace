package com.kh.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //필수생성자
@ToString 			//DB에서 값이 제대로넘어왔는지 체크용
public class UserProfile {

	private int userId;
	private String username;
	private String profileImageUrl;
	private LocalDateTime createdAt;
	// Local맛집 = 현재, 현지맛집
	// Localhost = 현재내주소
	// LocalDateTime = 현재 날짜,시간
}
