package com.imarker.parse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * use to mark the class whether can be stored to Parse.com
 *
 * @author handrenliang#gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ParseClass {

	/**
	 * class name in Parse.com，default value is {@link Class#getSimpleName()}
	 * @return class name
	 */
	String className() default "";
	
}
