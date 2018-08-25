package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.BestTitleVOSingleton;
import io.kang.vo.BestTitleVO;
import org.junit.Assert;
import org.junit.Test;

public class BestTitleVOUnitTest {
    @Test
    public void built_to_vo_test(){
        BestTitleVO bestTitleVO = BestTitleVO.builtToVO(TitleDTOUpdateSingleton.INSTANCE.getInstance(), 0L);
        Assert.assertEquals(bestTitleVO, BestTitleVOSingleton.INSTANCE.getInstance());
    }
}
