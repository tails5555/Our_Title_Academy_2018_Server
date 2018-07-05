package io.kang.unit_test.enumeration_unit;

import io.kang.enumeration.Type;
import org.junit.Assert;
import org.junit.Test;

public class TypeUnitTest {
    @Test
    public void typeEnumUserInsideTest(){
        Type type = Type.USER;
        Assert.assertEquals(Type.valueOf("USER"), type);
    }

    @Test
    public void typeEnumManagerInsideTest(){
        Type type = Type.MANAGER;
        Assert.assertEquals(Type.valueOf("MANAGER"), type);
    }

    @Test
    public void typeEnumAdminInsideTest(){
        Type type = Type.ADMIN;
        Assert.assertEquals(Type.valueOf("ADMIN"), type);
    }
}
