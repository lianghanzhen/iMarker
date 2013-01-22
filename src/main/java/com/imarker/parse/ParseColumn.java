package com.imarker.parse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * use to mark the field whether can be store to Parse.com
 *
 * @author handrenliang#gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ParseColumn {

	/**
	 * column name in Parse.com，default value is {@link java.lang.reflect.Field#getName()}
	 * @return column name
	 */
	String columnName() default "";

    /**
     * column type that represent how to deal with the column
     * @return column type {@link ColumnType}
     */
    ColumnType columnType() default ColumnType.COMMON;

    /**
     * whether fetch the related ParseObject, only work with ColumnType.RELATION
     * @return
     */
    boolean fetchIfNeed() default false;

    /**
     * column type that represent how to deal with the column
     */
    public static enum ColumnType {
        COMMON, /** common column that do not use {@link ParseProcessor} to process */
        RELATION /** relation column that use {@link ParseProcessor} to process */
    }
	
}
