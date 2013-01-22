package com.imarker.parse;

import java.lang.annotation.*;

/**
 * use to mark this class as Parse.com User class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface ParseUser {

}
