package com.nonobank.apps.utils.data;

public enum RegisterResult {
	SUCCESS(1, "注册成功", "正例-检查注册成功", ConstantUtils.CORRECT_CHECK_CODE, ConstantUtils.CORRECT_VALIDATION), MOBLIE_NULL(2,
			"注册成功", "正例-检查注册成功", ConstantUtils.CORRECT_CHECK_CODE, ConstantUtils.CORRECT_VALIDATION);
	private int code;
	private String message;
	private String comment;
	private String checkCode;
	private String validation;

	private RegisterResult(int code, String message, String comment, String checkCode, String validation) {
		this.code = code;
		this.message = message;
		this.comment = comment;
		this.checkCode = checkCode;
		this.validation = validation;
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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

}
