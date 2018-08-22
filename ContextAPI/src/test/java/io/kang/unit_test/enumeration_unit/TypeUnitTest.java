package io.kang.unit_test.enumeration_unit;

import io.kang.enumeration.Type;
import org.junit.Assert;
import org.junit.Test;

public class TypeUnitTest {
    @Test
    public void typeEnumCommentInsideTest(){
        final String type = "COMMENT";
        Assert.assertTrue(Type.COMMENT == type);
    }

    @Test
    public void typeEnumRequestInsideTest(){
        final String type = "REQUEST";
        Assert.assertTrue(Type.REQUEST == type);
    }

    @Test
    public void typeEnumTitleInsideTest(){
        final String type = "TITLE";
        Assert.assertTrue(Type.TITLE == type);
    }
}
