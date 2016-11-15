package com.nonobank.apps.utils.webintegration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Info {
	String desc();
	String dependency();
	boolean isDisabled();
}
