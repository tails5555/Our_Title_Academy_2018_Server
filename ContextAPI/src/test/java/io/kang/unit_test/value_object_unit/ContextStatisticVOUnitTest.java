package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.ContextStatisticVOSingleton;
import io.kang.vo.ContextStatisticVO;
import org.junit.Assert;
import org.junit.Test;

public class ContextStatisticVOUnitTest {
    @Test
    public void built_to_vo_test(){
        ContextStatisticVO contextStatisticVO = ContextStatisticVO.builtToVO(CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), 0L, 0L);
        Assert.assertEquals(contextStatisticVO, ContextStatisticVOSingleton.INSTANCE.getInstance());
    }
}
