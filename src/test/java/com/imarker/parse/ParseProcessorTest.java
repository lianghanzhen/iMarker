package com.imarker.parse;

import com.imarker.IMarkerTestRunner;
import com.imarker.model.Image;
import com.imarker.model.Marker;
import com.parse.ParseObject;
import static junit.framework.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(IMarkerTestRunner.class)
public class ParseProcessorTest {

    public static final String COLUMN_VALUE = "Test";
    private static ParseProcessor parseProcessor;

    @BeforeClass
    public static void setup() {
        parseProcessor = ParseProcessor.getInstance();
    }

    /** [start] test {@link ParseProcessor#toParseObject(Object)} */

    @Test(expected = ParseProcessException.class)
    public void testClassMustHasParseClassOrParseUserAnnotation() throws ParseProcessException {
        TestClassWithoutAnyParseAnnotation testClass = new TestClassWithoutAnyParseAnnotation();
        parseProcessor.toParseObject(testClass);
    }

    @Test(expected = ParseProcessException.class)
    public void testClassWithParseClassThatNameCannotStartWithUnderline() throws ParseProcessException {
        TestClassWithUnderline testClass = new TestClassWithUnderline();
        parseProcessor.toParseObject(testClass);
    }

    @Test
    public void testToParseObjectWithNull() throws ParseProcessException {
        assertNull(parseProcessor.toParseObject(null));
    }

    @Test
    public void testToParseObjectWithImageAndMarker() throws ParseProcessException {
        Marker marker = new Marker("username", "email", false, 0);
        Image image = new Image("title", "url", "contentType", 100, 10, 10, marker.getUsername(), marker);
        ParseObject markerObject = parseProcessor.toParseObject(marker);
        ParseObject imageObject = parseProcessor.toParseObject(image);
        assertNotNull(markerObject);
        assertNotNull(imageObject);
        assertTrue(markerObject instanceof com.parse.ParseUser);
        assertEquals(4, markerObject.keySet().size());
        assertFalse(markerObject.containsKey("objectId"));
        assertTrue(markerObject.containsKey("username"));
        assertEquals(marker.getUsername(), markerObject.get("username"));
        assertTrue(markerObject.containsKey("email"));
        assertEquals(marker.getEmail(), markerObject.get("email"));
        assertTrue(markerObject.containsKey("emailVerified"));
        assertEquals(marker.isEmailVerified(), markerObject.getBoolean("emailVerified"));
        assertTrue(markerObject.containsKey("gender"));
        assertEquals(marker.getGender(), markerObject.getInt("gender"));
        assertFalse(markerObject.containsKey("createdAt"));
        assertNull(markerObject.getDate("createdAt"));
        assertFalse(markerObject.containsKey("ACL"));

        assertEquals(9, imageObject.keySet().size());
        assertFalse(imageObject.containsKey("objectId"));
        assertTrue(imageObject.containsKey("title"));
        assertEquals(image.getTitle(), imageObject.getString("title"));
        assertTrue(imageObject.containsKey("url"));
        assertEquals(image.getUrl(), imageObject.getString("url"));
        assertTrue(imageObject.containsKey("contentType"));
        assertEquals(image.getContentType(), imageObject.getString("contentType"));
        assertTrue(imageObject.containsKey("size"));
        assertEquals(image.getSize(), imageObject.getInt("size"));
        assertTrue(imageObject.containsKey("width"));
        assertEquals(image.getWidth(), imageObject.getInt("width"));
        assertTrue(imageObject.containsKey("height"));
        assertEquals(image.getHeight(), imageObject.getInt("height"));
        assertTrue(imageObject.containsKey("marker"));
        assertEquals(image.getMarker().getUsername(), imageObject.getParseObject("marker").getString("username"));
        assertTrue(imageObject.containsKey("markerName"));
        assertEquals(image.getMarkerName(), imageObject.getString("markerName"));
        assertTrue(imageObject.containsKey("ACL"));
    }

    /** [end] test {@link ParseProcessor#toParseObject(Object)} */



    /** [start] test {@link ParseProcessor#fromParseObject(Class, com.parse.ParseObject)} */

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectThatMustHaveAnnotation() throws ParseProcessException {
        parseProcessor.fromParseObject(TestClassWithoutAnyParseAnnotation.class, new ParseObject("TestClassWithoutAnyParseAnnotation"));
    }

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectThatCannotHaveUnderline() throws ParseProcessException {
        parseProcessor.fromParseObject(TestClassWithUnderline.class, new ParseObject("_TestClass"));
    }

    @Test
    public void testFromParseObjectWithImageAndMarker() throws ParseProcessException {
        com.parse.ParseUser parseUser = new com.parse.ParseUser();
        parseUser.setUsername("username");
        parseUser.setEmail("email");
        parseUser.setPassword("password");
        parseUser.put("emailVerified", false);
        parseUser.put("gender", 0);
        Marker marker = parseProcessor.fromParseObject(Marker.class, parseUser);
        assertNotNull(marker);
        assertNotNull(parseUser);
        assertEquals(parseUser.getUsername(), marker.getUsername());
        assertEquals(parseUser.getEmail(), marker.getEmail());
        assertEquals(parseUser.getCreatedAt(), marker.getCreatedAt());
        assertEquals(parseUser.get("gender"), marker.getGender());
        assertEquals(parseUser.getObjectId(), marker.getObjectId());
        assertEquals(parseUser.get("emailVerified"), marker.isEmailVerified());

        ParseObject parseObject = new ParseObject("Image");
        parseObject.put("title", "title");
        parseObject.put("contentType", "contentType");
        parseObject.put("url", "url");
        parseObject.put("size", 100);
        parseObject.put("width", 10);
        parseObject.put("height", 10);
        parseObject.put("markerName", marker.getUsername());
        parseObject.put("marker", parseUser);
        Image image = parseProcessor.fromParseObject(Image.class, parseObject);
        assertNotNull(image);
        assertEquals(parseObject.getString("title"), image.getTitle());
        assertEquals(parseObject.getString("contentType"), image.getContentType());
        assertEquals(parseObject.getString("url"), image.getUrl());
        assertEquals(parseObject.getInt("size"), image.getSize());
        assertEquals(parseObject.getInt("width"), image.getWidth());
        assertEquals(parseObject.getInt("height"), image.getHeight());
        assertEquals(parseObject.get("markerName"), image.getMarkerName());
        assertEquals(marker, image.getMarker());
    }

    /** [end] test {@link ParseProcessor#fromParseObject(Class, com.parse.ParseObject)} */



    // [start] helper class

    private class TestClassWithoutAnyParseAnnotation {}

    @ParseClass(className = "_TestClass")
    private class TestClassWithUnderline {}

    // [end helper class]

}



