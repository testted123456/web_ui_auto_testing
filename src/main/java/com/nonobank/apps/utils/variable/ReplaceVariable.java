package com.nonobank.apps.utils.variable;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ReplaceVariable {

	private static Logger logger = LogManager.getLogger(ReplaceVariable.class);

	// 替换${}@{}%{}变量
	public static String handleVarible(String value) {

		if (value.contains("${") && value.contains("}")) {
			value = handleDollarPlaceholder(value);
		}

		return value;
	}

	// 处理${}
	public static String handleDollarPlaceholder(String value) {
		String valueAfterReplace = value;
		Pattern pattern = Pattern.compile("\\$\\{[A-Za-z0-9_\\.\\(\\)]*?\\}");
		Matcher matcher = pattern.matcher(valueAfterReplace);
		String var = null;
		String sub_var = null;

		while (matcher.find()) {
			var = matcher.group();
			// 去掉${}
			sub_var = var.substring(2, var.length() - 1).trim();

			String[] array = sub_var.split("\\(");
			String[] classAndMethod = array[0].split("\\.");
			String clazz = classAndMethod[0];
			String method = classAndMethod[1];
			String[] paramsArray = null;

			if (array.length == 2) {
				String params = array[1].trim();
				if (!params.equals(")")) {
					params = params.substring(0, params.length() - 1);
					int lenOfParams = params.length();
					paramsArray = params.split(",");
				}
			} else {
				String msg = "[ReplaceVariable]获取变量值失败...";
				logger.error(msg);
				Assert.fail(msg);
			}

			valueAfterReplace = getValueByReflect(clazz, method, paramsArray).toString();
		}

		return valueAfterReplace;
	}

	public static Object getValueByReflect(String stringOfClazz, String stringOfMethod, String[] params) {
		Class[] arrayParamClass = null;
		int lenOfParams = 0;

		if (params != null) {
			lenOfParams = params.length;
			arrayParamClass = new Class[lenOfParams];
			for (int i = 0; i < lenOfParams; i++) {
				arrayParamClass[i] = String.class;
			}
		}

		Class<?> clazz = null;

		try {
			clazz = Class.forName("com.nonobank.apps.utils.data." + stringOfClazz);

			Object ins = null;

			if (clazz.getName().equals("com.nonobank.apps.utils.RandomUtils")) {
				Method getInstance = clazz.getMethod("getInstance", null);
				ins = getInstance.invoke(null, null);
			}

			Method method = clazz.getMethod(stringOfMethod, arrayParamClass);
			Object obj = null;

			if (ins != null) {
				obj = method.invoke(ins, params);
			} else {
				obj = method.invoke(clazz.newInstance(), params);
			}

			return obj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String str1 = handleVarible("${RandomUtils.generateUserName()}");
		System.out.println("**************************str1=" + str1);
	}
}
