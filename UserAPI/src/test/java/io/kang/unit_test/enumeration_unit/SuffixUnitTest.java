package io.kang.unit_test.enumeration_unit;

import io.kang.enumeration.Suffix;
import org.junit.Assert;
import org.junit.Test;

public class SuffixUnitTest {
    @Test
    public void suffixEnumGifInsideTest() {
        Suffix suffix = Suffix.GIF;
        Assert.assertEquals(Suffix.valueOf("GIF"), suffix);
    }

    @Test
    public void suffixEnumJpgInsideTest(){
        Suffix suffix = Suffix.JPG;
        Assert.assertEquals(Suffix.valueOf("JPG"), suffix);
    }

    @Test
    public void suffixEnumJpegInsideTest(){
        Suffix suffix = Suffix.JPEG;
        Assert.assertEquals(Suffix.valueOf("JPEG"), suffix);
    }

    @Test
    public void suffixEnumPngInsideTest(){
        Suffix suffix = Suffix.PNG;
        Assert.assertEquals(Suffix.valueOf("PNG"), suffix);
    }
}
