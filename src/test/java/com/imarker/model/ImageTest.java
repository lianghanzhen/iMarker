package com.imarker.model;

import com.imarker.IMarkerTestRunner;
import com.imarker.parse.ParseProcessException;
import com.imarker.parse.ParseProcessor;
import com.parse.ParseObject;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(IMarkerTestRunner.class)
public class ImageTest {

    private static ParseProcessor parseProcessor;

    @BeforeClass
    public static void setup() {
        parseProcessor = ParseProcessor.getInstance();
    }

    @Test
    public void testToParseObjectWithImage() throws ParseProcessException {
        Image image = new Image();
        image.setContentType("ContentType");
        image.setHeight(10);
        image.setWidth(10);
        image.setMarkerName("lianghanzhen");
        image.setSize(100);
        image.setTitle("title");
        image.setUrl("http://imarker.me");
//        Marker marker = new Marker();
//        image.setMarker(marker);
        ParseObject parseObject = parseProcessor.toParseObject(image);
        Assert.assertNotNull(parseObject);
        Assert.assertEquals(7, parseObject.keySet().size());
        Assert.assertEquals("ContentType", parseObject.getString("contentType"));
        Assert.assertEquals(10, parseObject.getInt("height"));
        Assert.assertEquals(10, parseObject.getInt("width"));
        Assert.assertEquals(100, parseObject.getInt("size"));
        Assert.assertEquals("title", parseObject.getString("title"));
        Assert.assertEquals("http://imarker.me", parseObject.getString("url"));
        Assert.assertEquals("lianghanzhen", parseObject.getString("markerName"));
//        Assert.assertEquals("_User", parseObject.getParseObject("marker").getClassName());
    }

    @Test
    public void testFromParseObjectWithImage() throws Exception {
        ParseObject parseObject = ParseObject.create("Image");
        parseObject.put("contentType", "ContentType");
        parseObject.put("height", 10);
        parseObject.put("width", 10);
        parseObject.put("size", 100);
        parseObject.put("title", "title");
        parseObject.put("url", "http://imarker.me");
        parseObject.put("markerName", "lianghanzhen");
        Image image = parseProcessor.fromParseObject(Image.class, parseObject);
        Assert.assertNotNull(image);
        Assert.assertEquals("ContentType", image.getContentType());
        Assert.assertEquals(10, image.getHeight());
        Assert.assertEquals(10, image.getWidth());
        Assert.assertEquals(100, image.getSize());
        Assert.assertEquals("title", image.getTitle());
        Assert.assertEquals("http://imarker.me", image.getUrl());
        Assert.assertEquals("lianghanzhen", image.getMarkerName());
        Assert.assertNull(image.getMarker());
    }
}
