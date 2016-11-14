package com.nonobank.apps.utils.webintegration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Params {
	String [] type();
	String [] name();
	String [] desc();
}
