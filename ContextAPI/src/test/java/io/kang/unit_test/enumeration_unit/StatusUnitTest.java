package io.kang.unit_test.enumeration_unit;

import io.kang.enumeration.Status;
import org.junit.Assert;
import org.junit.Test;

public class StatusUnitTest {
    @Test
    public void statusEnumLikeInsideTest() {
        Status status = Status.LIKE;
        Assert.assertEquals(Status.valueOf("LIKE"), status);
    }

    @Test
    public void statusEnumHateInsideTest() {
        Status status = Status.HATE;
        Assert.assertEquals(Status.valueOf("HATE"), status);
    }
}
