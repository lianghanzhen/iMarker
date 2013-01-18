package com.imarker.parse;

import com.imarker.IMarkerTestRunner;
import com.parse.ParseObject;
import junit.framework.Assert;
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
    public void testToParseObjectWithoutParseClassAnnotation() throws ParseProcessException {
        ObjectWithoutParseClassAnnotation objectWithoutParseClassAnnotation = new ObjectWithoutParseClassAnnotation();
        parseProcessor.toParseObject(objectWithoutParseClassAnnotation);
    }

    @Test
    public void testToParseObjectWithParseClassAnnotation() throws ParseProcessException {
        ObjectWithParseClassAnnotation objectWithParseClassAnnotation = new ObjectWithParseClassAnnotation();
        ParseObject parseObject = new ParseObject(ObjectWithParseClassAnnotation.class.getSimpleName());
        Assert.assertEquals(parseObject.getClassName(), parseProcessor.toParseObject(objectWithParseClassAnnotation).getClassName());
    }

    @Test(expected = ParseProcessException.class)
    public void testToParseObjectWithoutParseClassAndColumnAnnotation() throws ParseProcessException {
        ObjectWithoutParseClassAndColumnAnnotation objectWithoutParseClassAndColumnAnnotation = new ObjectWithoutParseClassAndColumnAnnotation(COLUMN_VALUE);
        parseProcessor.toParseObject(objectWithoutParseClassAndColumnAnnotation);
    }

    @Test
    public void testToParseObjectWithParseClassButWithoutParseColumnAnnotation() throws ParseProcessException {
        ObjectWithParseClassButWithoutParseColumnAnnotation objectWithParseClassButWithoutParseColumnAnnotation = new ObjectWithParseClassButWithoutParseColumnAnnotation(COLUMN_VALUE);
        ParseObject parseObject = new ParseObject(ObjectWithParseClassButWithoutParseColumnAnnotation.class.getSimpleName());
        ParseObject testParseObject = parseProcessor.toParseObject(objectWithParseClassButWithoutParseColumnAnnotation);
        Assert.assertEquals(parseObject.getClassName(), testParseObject.getClassName());
        Assert.assertEquals(0, testParseObject.keySet().size());
    }

    @Test
    public void testToParseObjectWithParseClassAndParseColumnAnnotation() throws ParseProcessException {
        ObjectWithParseClassAndParseColumnAnnotation objectWithParseClassAndParseColumnAnnotation = new ObjectWithParseClassAndParseColumnAnnotation(COLUMN_VALUE);
        ParseObject parseObject = new ParseObject(ObjectWithParseClassAndParseColumnAnnotation.class.getSimpleName());
        ParseObject testParseObject = parseProcessor.toParseObject(objectWithParseClassAndParseColumnAnnotation);
        Assert.assertEquals(parseObject.getClassName(), testParseObject.getClassName());
        Assert.assertEquals(1, testParseObject.keySet().size());
        Assert.assertEquals(true, testParseObject.containsKey("column"));
    }

    @Test
    public void testToParseObjectWithSpecifyParseClassAndParseColumnAnnotation() throws ParseProcessException {
        ObjectWithSpecifyParseClassAndParseColumnAnnotation objectWithSpecifyParseClassAndParseColumnAnnotation = new ObjectWithSpecifyParseClassAndParseColumnAnnotation(COLUMN_VALUE, "title");
        ParseObject parseObject = new ParseObject("TestParseClass");
        ParseObject testParseObject = parseProcessor.toParseObject(objectWithSpecifyParseClassAndParseColumnAnnotation);
        Assert.assertEquals(parseObject.getClassName(), testParseObject.getClassName());
        Assert.assertEquals(1, testParseObject.keySet().size());
        Assert.assertEquals(true, testParseObject.containsKey("testParseColumn"));
        Assert.assertEquals(false, testParseObject.containsKey("column"));
        Assert.assertEquals(false, testParseObject.containsKey("title"));
    }

    @Test
    public void testToParseObjectWithParseReserveColumnName() throws ParseProcessException {
        ObjectWithParseReverveColumnName objectWithParseReverveColumnName = new ObjectWithParseReverveColumnName("objectId", new Date(), new Date());
        ParseObject parseObject = new ParseObject(ObjectWithParseReverveColumnName.class.getSimpleName());
        ParseObject testParseObject = parseProcessor.toParseObject(objectWithParseReverveColumnName);
        Assert.assertEquals(parseObject.getClassName(), testParseObject.getClassName());
        Assert.assertEquals(0, testParseObject.keySet().size());
        Assert.assertEquals(false, testParseObject.containsKey("objectId"));
        Assert.assertEquals(false, testParseObject.containsKey("createdAt"));
        Assert.assertEquals(false, testParseObject.containsKey("updatedAt"));
    }

    @Test
    public void testToParseObjectWithParseRelation() throws ParseProcessException {
        ObjectWithParseClassAndParseColumnAnnotation obj1 = new ObjectWithParseClassAndParseColumnAnnotation("Test1");
        ObjectWithParseClassAndParseColumnAnnotation obj2 = new ObjectWithParseClassAndParseColumnAnnotation("Test2");
        String commonColumnWithFetchIfNeed = "commonColumnWithFetchIfNeed";
        ObjectWithParseRelation objectWithParseRelation = new ObjectWithParseRelation(obj1, obj2, commonColumnWithFetchIfNeed);
        ParseObject parseObject = new ParseObject(ObjectWithParseRelation.class.getSimpleName());
        ParseObject testParseObject = parseProcessor.toParseObject(objectWithParseRelation);
        Assert.assertEquals(parseObject.getClassName(), testParseObject.getClassName());
        Assert.assertEquals(2, testParseObject.keySet().size());
        Assert.assertEquals(false, testParseObject.containsKey("relationColumn"));
        Assert.assertEquals(true, testParseObject.containsKey("relationFetchColumn"));
        Assert.assertEquals(true, testParseObject.containsKey("commonColumnWithFetchIfNeed"));
    }

    /** [end] test {@link ParseProcessor#toParseObject(Object)} */

    /** [start] test {@link ParseProcessor#fromParseObject(Class, com.parse.ParseObject)} */

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectWithoutParseClassAnnotation() throws ParseProcessException {
        parseProcessor.fromParseObject(ObjectWithoutParseClassAnnotation.class, new ParseObject(ObjectWithoutParseClassAnnotation.class.getSimpleName()));
    }

    @Test
    public void testFromParseObjectWithParseClassAnnotation() throws ParseProcessException {
        Assert.assertNotNull(parseProcessor.fromParseObject(ObjectWithParseClassAnnotation.class, new ParseObject(ObjectWithParseClassAnnotation.class.getSimpleName())));
    }

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectWithoutParseClassAndColumnAnnotation() throws ParseProcessException {
        parseProcessor.fromParseObject(ObjectWithoutParseClassAndColumnAnnotation.class, new ParseObject(ObjectWithoutParseClassAndColumnAnnotation.class.getSimpleName()));
    }

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectWithParseClassButWithoutDefaultConstructor() throws ParseProcessException {
        parseProcessor.fromParseObject(ObjectWithParseClassButWithoutDefaultConstructor.class, new ParseObject(ObjectWithParseClassButWithoutDefaultConstructor.class.getSimpleName()));
    }

    @Test
    public void testFromParseObjectWithParseClassButWithoutParseColumnAnnotation() throws ParseProcessException {
        ObjectWithParseClassButWithoutParseColumnAnnotation objectWithParseClassButWithoutParseColumnAnnotation = parseProcessor.fromParseObject(ObjectWithParseClassButWithoutParseColumnAnnotation.class, new ParseObject(ObjectWithParseClassButWithoutParseColumnAnnotation.class.getSimpleName()));
        Assert.assertNotNull(objectWithParseClassButWithoutParseColumnAnnotation);
        Assert.assertNull(objectWithParseClassButWithoutParseColumnAnnotation.getColumn());
    }

    @Test
    public void testFromParseObjectWithParseClassAndParseColumnAnnotation() throws ParseProcessException {
        ParseObject parseObject = new ParseObject(ObjectWithParseClassAndParseColumnAnnotation.class.getSimpleName());
        parseObject.put("column", COLUMN_VALUE);
        ObjectWithParseClassAndParseColumnAnnotation objectWithParseClassAndParseColumnAnnotation = parseProcessor.fromParseObject(ObjectWithParseClassAndParseColumnAnnotation.class, parseObject);
        Assert.assertNotNull(objectWithParseClassAndParseColumnAnnotation);
        Assert.assertEquals(COLUMN_VALUE, objectWithParseClassAndParseColumnAnnotation.getColumn());
    }

    @Test(expected = ParseProcessException.class)
    public void testFromParseObjectWithSpecifyParseClassAndParseColumnAnnotation1() throws ParseProcessException {
        ParseObject parseObject = new ParseObject(ObjectWithSpecifyParseClassAndParseColumnAnnotation.class.getSimpleName());
        parseObject.put("column", COLUMN_VALUE);
        parseObject.put("title", "title");
        ObjectWithSpecifyParseClassAndParseColumnAnnotation objectWithSpecifyParseClassAndParseColumnAnnotation = parseProcessor.fromParseObject(ObjectWithSpecifyParseClassAndParseColumnAnnotation.class, parseObject);
    }

    @Test
    public void testFromParseObjectWithSpecifyParseClassAndParseColumnAnnotation2() throws ParseProcessException {
        ParseObject parseObject = new ParseObject("TestParseClass");
        parseObject.put("testParseColumn", COLUMN_VALUE);
        parseObject.put("title", "title");
        ObjectWithSpecifyParseClassAndParseColumnAnnotation objectWithSpecifyParseClassAndParseColumnAnnotation = parseProcessor.fromParseObject(ObjectWithSpecifyParseClassAndParseColumnAnnotation.class, parseObject);
        Assert.assertNotNull(objectWithSpecifyParseClassAndParseColumnAnnotation);
        Assert.assertNotNull(objectWithSpecifyParseClassAndParseColumnAnnotation.getColumn());
        Assert.assertNull(objectWithSpecifyParseClassAndParseColumnAnnotation.getTitle());
    }

    @Test
    public void testFromParseObjectWithParseReserveColumnName() throws ParseProcessException {
        ParseObject parseObject = new ParseObject(ObjectWithParseReverveColumnName.class.getSimpleName());
        String objectId = "objectId";
        Date createdAt = new Date();
        Date updatedAt = new Date();
        parseObject.put(objectId, objectId);
        parseObject.put("createdAt", createdAt);
        parseObject.put("updatedAt", updatedAt);
        ObjectWithParseReverveColumnName objectWithParseReverveColumnName = parseProcessor.fromParseObject(ObjectWithParseReverveColumnName.class, parseObject);
        Assert.assertNotNull(objectWithParseReverveColumnName);
        Assert.assertEquals(objectId, objectWithParseReverveColumnName.getObjectId());
        Assert.assertEquals(createdAt, objectWithParseReverveColumnName.getCreatedAt());
        Assert.assertEquals(updatedAt, objectWithParseReverveColumnName.getUpdatedAt());
    }

    @Test
    public void testFromParseObjectWithParseRelation() throws ParseProcessException {
        ParseObject parseObject1 = new ParseObject(ObjectWithParseClassAndParseColumnAnnotation.class.getSimpleName());
        parseObject1.put("column", "column1");
        ParseObject parseObject2 = new ParseObject(ObjectWithParseClassAndParseColumnAnnotation.class.getSimpleName());
        parseObject2.put("column", "column2");
        ParseObject parseObject = new ParseObject(ObjectWithParseRelation.class.getSimpleName());
        parseObject.put("relationColumn", parseObject1);
        parseObject.put("relationFetchColumn", parseObject2);
        parseObject.put("commonColumnWithFetchIfNeed", "commonColumnWithFetchIfNeed");
        ObjectWithParseClassAndParseColumnAnnotation obj1 = parseProcessor.fromParseObject(ObjectWithParseClassAndParseColumnAnnotation.class, parseObject1);
        ObjectWithParseClassAndParseColumnAnnotation obj2 = parseProcessor.fromParseObject(ObjectWithParseClassAndParseColumnAnnotation.class, parseObject2);
        ObjectWithParseRelation objectWithParseRelation = parseProcessor.fromParseObject(ObjectWithParseRelation.class, parseObject);
        Assert.assertNotNull(obj1);
        Assert.assertNotNull(obj2);
        Assert.assertNotNull(objectWithParseRelation);
        Assert.assertEquals(obj1, objectWithParseRelation.getRelationColumn());
        Assert.assertEquals(obj2, objectWithParseRelation.getRelationFetchColumn());
        Assert.assertEquals("commonColumnWithFetchIfNeed", objectWithParseRelation.getCommonColumnWithFetchIfNeed());
    }

    /** [end] test {@link ParseProcessor#fromParseObject(Class, com.parse.ParseObject)} */

}

class ObjectWithoutParseClassAnnotation {
    ObjectWithoutParseClassAnnotation() {}
}

@ParseClass
class ObjectWithParseClassAnnotation {
    ObjectWithParseClassAnnotation() {}
}

class ObjectWithoutParseClassAndColumnAnnotation {
    private String column;

    ObjectWithoutParseClassAndColumnAnnotation(String column) {
        this.column = column;
    }
}

@ParseClass
class ObjectWithParseClassButWithoutDefaultConstructor {
    private String column;

    ObjectWithParseClassButWithoutDefaultConstructor(String column) {
        this.column = column;
    }
}

@ParseClass
class ObjectWithParseClassButWithoutParseColumnAnnotation {
    private String column;

    ObjectWithParseClassButWithoutParseColumnAnnotation() {}

    ObjectWithParseClassButWithoutParseColumnAnnotation(String column) {
        this.column = column;
    }

    String getColumn() {
        return this.column;
    }
}

@ParseClass
class ObjectWithParseClassAndParseColumnAnnotation {
    private @ParseColumn String column;

    ObjectWithParseClassAndParseColumnAnnotation() {}

    ObjectWithParseClassAndParseColumnAnnotation(String column) {
        this.column = column;
    }

    String getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectWithParseClassAndParseColumnAnnotation that = (ObjectWithParseClassAndParseColumnAnnotation) o;

        if (!column.equals(that.column)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return column.hashCode();
    }
}

@ParseClass(className = "TestParseClass")
class ObjectWithSpecifyParseClassAndParseColumnAnnotation {
    private @ParseColumn(columnName = "testParseColumn") String column;
    private String title;

    ObjectWithSpecifyParseClassAndParseColumnAnnotation() {}

    ObjectWithSpecifyParseClassAndParseColumnAnnotation(String column, String title) {
        this.column = column;
        this.title = title;
    }

    String getColumn() {
        return column;
    }

    String getTitle() {
        return title;
    }
}

@ParseClass
class ObjectWithParseReverveColumnName {
    private @ParseColumn String objectId;
    private @ParseColumn Date createdAt;
    private @ParseColumn Date updatedAt;

    ObjectWithParseReverveColumnName() {}

    ObjectWithParseReverveColumnName(String objectId, Date createdAt, Date updatedAt) {
        this.objectId = objectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    String getObjectId() {
        return objectId;
    }

    Date getCreatedAt() {
        return createdAt;
    }

    Date getUpdatedAt() {
        return updatedAt;
    }
}

@ParseClass
class ObjectWithParseRelation {
    @ParseColumn(columnType = ParseColumn.ColumnType.RELATION)
    private ObjectWithParseClassAndParseColumnAnnotation relationColumn;
    @ParseColumn(columnType = ParseColumn.ColumnType.RELATION, fetchIfNeed = true)
    private ObjectWithParseClassAndParseColumnAnnotation relationFetchColumn;
    @ParseColumn(columnType = ParseColumn.ColumnType.COMMON, fetchIfNeed = true)
    private String commonColumnWithFetchIfNeed;

    ObjectWithParseRelation() {}

    ObjectWithParseRelation(ObjectWithParseClassAndParseColumnAnnotation relationColumn, ObjectWithParseClassAndParseColumnAnnotation relationFetchColumn, String commonColumnWithFetchIfNeed) {
        this.relationColumn = relationColumn;
        this.relationFetchColumn = relationFetchColumn;
        this.commonColumnWithFetchIfNeed = commonColumnWithFetchIfNeed;
    }

    ObjectWithParseClassAndParseColumnAnnotation getRelationColumn() {
        return relationColumn;
    }

    ObjectWithParseClassAndParseColumnAnnotation getRelationFetchColumn() {
        return relationFetchColumn;
    }

    String getCommonColumnWithFetchIfNeed() {
        return commonColumnWithFetchIfNeed;
    }
}
