package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.BriefFetchRequestVOSingleton;
import io.kang.vo.BriefFetchRequestVO;
import org.junit.Assert;
import org.junit.Test;

public class BriefFetchRequestVOUnitTest {
    @Test
    public void built_to_vo_test(){
        BriefFetchRequestVO briefFetchRequestVO = BriefFetchRequestVO.builtToVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), "TITLE_CONTEXT_01", 0L, 0L, 0L);
        Assert.assertEquals(briefFetchRequestVO, BriefFetchRequestVOSingleton.INSTANCE.getInstance());
    }
}
