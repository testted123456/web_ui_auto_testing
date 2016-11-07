package com.nonobank.apps.utils.data;

import com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static final Logger logger = LogManager.getLogger(StringUtils.class);

	/**
	 * m5 给定字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String md5(String data) {

		try {
			return new String(org.apache.commons.codec.digest.DigestUtils.md5Hex(data.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error("getMD5_error={}", e);
		}

		return org.apache.commons.lang3.StringUtils.EMPTY;
	}

	/**
	 * 根据条件连接字符传
	 * 
	 * @param stringArray
	 * @param function
	 * @param separator
	 *            分割符
	 * @return
	 */
	public static String join(String[] stringArray, Function<String, String> function, String separator) {
		StringBuffer sb = new StringBuffer();
		for (String s : stringArray) {
			sb.append(function.apply(s));
			sb.append(separator);
		}

		return sb.substring(0, sb.length() - separator.length()).toString();
	}

	/**
	 * 根据条件连接字符传
	 * 
	 * @param stringArray
	 * @param function
	 * @return
	 */
	public static String join(String[] stringArray, Function<String, String> function) {
		StringBuffer sb = new StringBuffer();
		for (String s : stringArray) {
			sb.append(function.apply(s));
		}

		return sb.substring(0, sb.length()).toString();
	}

	/**
	 * move to RestTemplateClient 给请求路径添加request parameter
	 * 
	 * @param sourceUrl
	 * @param parameters
	 * @return
	 */
	@Deprecated
	public static String appendWithMap(final String sourceUrl, Map<String, String> parameters) {
		String url = sourceUrl + "?";
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			if (null != entry.getValue() && !entry.getValue().trim().isEmpty()
					&& entry.getValue().trim().length() != 0) {
				url = url + entry.getKey() + "=" + entry.getValue() + "&&";
			}
		}
		if (url.endsWith("&&")) {
			return url.substring(0, url.length() - "&&".length());
		} else {
			return url;
		}

	}

	/**
	 * Formate Date to "yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date.getTime());
	}

	/**
	 * 根据正则表达式取子字符串，从匹配字符开始的地方去字符串
	 * 
	 * @param target
	 *            目标字符串
	 * @param regexp
	 *            正则表达式
	 * @param fromIndex
	 *            第N个匹配的地方
	 * @param includeRegExpFlag
	 *            包括匹配字符，或者不包括匹配字符
	 * @return
	 */
	public static String subString(String target, String regexp, int fromIndex, boolean includeRegExpFlag) {

		Pattern p = Pattern.compile(regexp);
		Matcher matcher = p.matcher(target);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (matcher.find()) {
			i++;
			if (i == fromIndex) {
				int index = includeRegExpFlag ? matcher.start() : matcher.end();
				sb.append(target.substring(index, target.length()));
				break;
			}

		}
		return sb.toString();
	}

	/**
	 * Big Decimal RoundUp to String
	 * 
	 * @param number
	 * @return
	 */
	public static String bigDecimalRoundUpToString(BigDecimal number) {

		if (number.compareTo(BigDecimal.ZERO) == 0) {
			return "0";
		}
		number = number.setScale(2, BigDecimal.ROUND_HALF_UP);
		return number.toString();
	}

	/**
	 * todo move to MapUtils Parse String to Map
	 * 
	 * @param input
	 * @param seperator
	 * @return
	 */
	@Deprecated
	public static Map<String, String> parseToMap(String input, String seperator) {
		Map<String, String> map = new HashMap<String, String>();
		String[] temp = input.split(";");
		for (String s : temp) {
			String[] kv = s.split("=");
			map.put(kv[0].trim(), kv[1].trim());
		}

		return map;
	}

	public static String toUTF8String(String input) {
		try {
			return new String(input.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return input;
		}
	}

	public static void main(String[] args) {
		String str = md5("it789123");
		System.out.println(str);
	}

}
