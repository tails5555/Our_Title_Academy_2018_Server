package io.kang.unit_test.value_object_unit;

import io.kang.domain.Detail;
import io.kang.unit_test.singleton_object.repository_testing.DetailUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.DetailVOSingleton;
import io.kang.vo.DetailVO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DetailVOUnitTest {
    private static Detail detail = DetailUpdateSingleton.INSTANCE.getInstance();
    private static DetailVO detailVO = DetailVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_vo_test() throws IOException {
        DetailVO detailVO = DetailVO.builtToVO(this.detail);
        Assert.assertEquals(detailVO, this.detailVO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        Detail detail = DetailVO.builtToDomain(this.detailVO);
        Assert.assertEquals(detail, this.detail);
    }
}
