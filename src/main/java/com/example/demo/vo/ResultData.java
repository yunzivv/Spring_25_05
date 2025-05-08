package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResultData { // 표준 데이터가 지켜야할 사항
	
	@Getter
	private String ResultCode; // 성공 여부
	@Getter
	private Object msg; // 메세지
	@Getter
	private Object data; // 추가 데이터

	public static ResultData from(String ResultCode, Object msg) {

		return from(ResultCode, msg, null);
	}

	public static ResultData from(String ResultCode, Object msg, Object data) {

		return new ResultData(ResultCode, msg, data);
	}
	
	public boolean isSuccess() { // is로 시작하는 함수는 자동으로 필드가 만들어진다.
		return ResultCode.startsWith("S");
	}
	
	public boolean isFailed() {
		return !isSuccess();
	}
}
