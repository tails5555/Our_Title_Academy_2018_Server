package io.kang.unit_test.value_object_unit;

import io.kang.domain.Age;
import io.kang.unit_test.singleton_object.repository_testing.AgeUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.vo.AgeVO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AgeVOUnitTest {
    private static final Age age = AgeUpdateSingleton.INSTANCE.getInstance();
    private static final AgeVO ageVO = AgeVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_vo_test() throws IOException {
        AgeVO ageVO = AgeVO.builtToVO(this.age);
        Assert.assertEquals(ageVO, this.ageVO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        Age age = AgeVO.builtToDomain(this.ageVO);
        Assert.assertEquals(age, this.age);
    }
}
