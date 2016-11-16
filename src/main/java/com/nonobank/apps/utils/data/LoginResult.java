package com.nonobank.apps.utils.data;

public enum LoginResult {
	SUCESS(1, "登录成功"), LOGINNAMENULL(2, "请输入您的用户名或手机号！");
	private int code;
	private String message;

	private LoginResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
