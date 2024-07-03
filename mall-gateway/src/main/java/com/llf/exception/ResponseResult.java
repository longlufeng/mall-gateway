package com.llf.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResponseResult {

	/**
	 * The code
	 */
	private String code;

	/**
	 * The message
	 */
	private String msg;

	/**
	 * The data
	 */
	private Object data;

	public ResponseResult(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}