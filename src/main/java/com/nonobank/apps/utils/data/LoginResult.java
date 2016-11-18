package com.nonobank.apps.utils.data;

public enum LoginResult {
	SUCESS(1, "登录成功", "正例——检查登录成功脚本"), LOGINNAME_NULL(2, "请输入您的用户名或手机号！", "反例——检查登录用户名为空脚本"), LOGINPWD_NULL(3,
			"请输入您的登录密码！", "反例——检查密码为空脚本");
	private int code;
	private String message;
	private String comment;

	private LoginResult(int code, String message, String comment) {
		this.code = code;
		this.message = message;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
