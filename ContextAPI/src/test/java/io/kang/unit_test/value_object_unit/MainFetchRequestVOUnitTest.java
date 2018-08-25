package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.BestTitleVOSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.MainFetchRequestVOSingleton;
import io.kang.vo.MainFetchRequestVO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MainFetchRequestVOUnitTest {
    @Test
    public void built_to_vo_test(){
        MainFetchRequestVO mainFetchRequestVO = MainFetchRequestVO.builtToVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), Arrays.asList(BestTitleVOSingleton.INSTANCE.getInstance()), 0L, 0L, false, false);
        Assert.assertEquals(mainFetchRequestVO, MainFetchRequestVOSingleton.INSTANCE.getInstance());
    }
}
