package com.imarker.parse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.imarker.Constants;
import com.parse.ParseObject;

public final class ParseProcessor {

	private static ParseProcessor instance;

    private ParseProcessor() {}

	public static ParseProcessor getInstance() {
		if (instance == null) {
			synchronized (ParseProcessor.class) {
				if (instance == null)
					instance = new ParseProcessor();
			}
		}
		return instance;
	}

	/**
	 * common object transform to ParseObject
	 * 1. if common object class has annotation {@see ParseClass}, then ParseObject's className is the annotation specify value
	 * 2. if common object class do not have annotation {@see ParseClass} or class name in (1) is empty, the class name is {@link Class#getSimpleName()}
	 * 3. if common object class has annotation {@see ParseClass}, then all the field will be stored to Parse.com
	 * 4. if common object class do not have annotation {@see ParseClass}, then only Fields that have annotation {@see ParseColumn} will be stored to Parse.com
	 * @param originalObject common object
	 * @return ParseObject
	 * @throws com.imarker.parse.ParseProcessException exception
	 */
	public <T> ParseObject toParseObject(T originalObject) throws ParseProcessException {
		try {
			Class<?> clazz = originalObject.getClass();
            if (!isParseClassAnnotationPresent(clazz))
                throw new ParseProcessException(String.format("Cannot parse %s to ParseObject, you must mark it with annotation ParseClass.", clazz.getName()));

            // according to ClassName, initial different ParseObject
            String className = getParseClassName(clazz);
            ParseObject parseObject = ParseObject.create(className);
			List<Field> fields = getParseColumns(clazz);
			for (Field field : fields) {
				field.setAccessible(true);
                if (!isParseReserveColumn(field)) {
                    String columnName = getParseColumnName(field);
                    if (isRelationColumn(field)) {
                        parseObject.put(columnName, toParseObject(field.get(originalObject)));
                    } else {
                        if (field.get(originalObject) != null)
                            parseObject.put(columnName, field.get(originalObject));
                    }
                }
			}
			return parseObject;	
		} catch (IllegalAccessException e) {
			throw new ParseProcessException(e);
		} catch (IllegalArgumentException e) {
			throw new ParseProcessException(e);
		}	
	}

    private boolean isParseReserveColumn(Field field) {
        String columnName = getParseColumnName(field);
        return Constants.PARSE_RESERVE_COLUMN_OBJECT_ID.equals(columnName) || Constants.PARSE_RESERVE_COLUMN_CREATED_AT.equals(columnName)
               || Constants.PARSE_RESERVE_COLUMN_UPDATED_AT.equals(columnName) || Constants.PARSE_RESERVE_COLUMN_ACL.equals(columnName);
    }

    private boolean isRelationColumn(Field field) {
        return field.isAnnotationPresent(ParseColumn.class) && field.getAnnotation(ParseColumn.class).columnType() == ParseColumn.ColumnType.RELATION;
    }
	
	/**
	 * clazz that construct from ParseObject must have default Constructor, because of using it to initialize.
	 * @param clazz that want to be constructed
	 * @param parseObject ParseObject
	 * @return new object
	 * @throws com.imarker.parse.ParseProcessException exception
	 */
	public <T> T fromParseObject(Class<T> clazz, ParseObject parseObject) throws ParseProcessException {
        if (!isParseClassAnnotationPresent(clazz) || !parseObject.getClassName().equals(getParseClassName(clazz)))
            throw new ParseProcessException(String.format("Cannot create %s object from ParseObject, you must mark it with annotation ParseClass.", clazz.getName()));
        if (Constants.isParseReserveClass(parseObject.getClassName()))
            throw new ParseProcessException("Cannot parse Parse.com reserve class " + parseObject.getClassName() + " to common object.");

		try {
			T result = clazz.newInstance();
			List<Field> fields = getParseColumns(clazz);
			for (Field field : fields) {
				field.setAccessible(true);
				String columnName = getParseColumnName(field);
				if (parseObject.containsKey(columnName))
					field.set(result, isRelationColumn(field) ? fromParseObject(field.getType(), parseObject.getParseObject(columnName)) : parseObject.get(columnName));
			}
			return result;
		} catch (IllegalAccessException e) {
			throw new ParseProcessException(e);
		} catch (InstantiationException e) {
			throw new ParseProcessException(e);
		}
	}
	
	/*
	 * get {@link ParseClass} class name
	 * @param clazz class
	 * @return class name
	 */
	private String getParseClassName(Class<?> clazz) throws ParseProcessException {
	    String className = !TextUtils.isEmpty(clazz.getAnnotation(ParseClass.class).className()) ? clazz.getAnnotation(ParseClass.class).className() : clazz.getSimpleName();
        if (Constants.isParseReserveClass(className))
            throw new ParseProcessException(className + " is Parse.com reserve class, remark it.");
        return className;
	}

	/*
	 * whether clazz has annotation {@link ParseClass}
	 * @param clazz
	 * @return
	 */
	private boolean isParseClassAnnotationPresent(Class<?> clazz) {
		return clazz.isAnnotationPresent(ParseClass.class);
	}

	/*
	 * get {@link ParseColumn}, add the fields that is marked as {@link ParseColumn}
	 * @param clazz class
	 * @return fields
	 */
	private List<Field> getParseColumns(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ParseColumn.class))
                fieldList.add(field);
        }
        return fieldList;
	}
	
	/*
	 * get {@link ParseColumn} name
	 * @param field field
	 * @return column name
	 */
	private String getParseColumnName(Field field) {
		return !TextUtils.isEmpty(field.getAnnotation(ParseColumn.class).columnName()) ? field.getAnnotation(ParseColumn.class).columnName() : field.getName();
	}
	
}
