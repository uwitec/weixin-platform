package com.xuchunchun.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RequestMsgField {
	String nodeName();
	boolean isComp() default false;
	String addValMethod();
	boolean isChanged() default false;
	Class parameter();
	boolean isArray() default false;
	String arrayNodeName() default "item";
}
