package com.nonobank.apps.utils.data;

public enum LoginResult {
	SUCESS(1, "登录成功", "正例——检查登录成功脚本", ConstantUtils.CORRECT_CHECK_CODE), LOGINNAME_NULL(2, "请输入您的用户名或手机号！",
			"反例——检查登录用户名为空脚本", ConstantUtils.CORRECT_CHECK_CODE), LOGINPWD_NULL(3, "请输入您的登录密码！", "反例——检查密码为空脚本",
					ConstantUtils.CORRECT_CHECK_CODE), CHECK_CODE_NULL(4, "请输入安全码！", "反例——检查安全码为空脚本",
							ConstantUtils.NULL_CHECK_CODE), LOGINNAME_ERROR(5, "登录用户名不存在", "反例——检查登录用户名错误",
									ConstantUtils.CORRECT_CHECK_CODE), CHECK_CODE_ERROR(6, "验证码错误", "反例——检查安全码输入错误",
											ConstantUtils.ERROR_CHECK_CODE);

	private int code;
	private String message;
	private String comment;
	private String checkCode;

	private LoginResult(int code, String message, String comment, String checkCode) {
		this.code = code;
		this.message = message;
		this.comment = comment;
		this.checkCode = checkCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
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
