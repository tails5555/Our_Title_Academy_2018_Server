package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.MainTitleVOSingleton;
import io.kang.vo.MainTitleVO;
import org.junit.Assert;
import org.junit.Test;

public class MainTitleVOUnitTest {
    @Test
    public void built_to_vo_test(){
        MainTitleVO mainTitleVO = MainTitleVO.builtToVO(TitleDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L, false, false);
        Assert.assertEquals(mainTitleVO, MainTitleVOSingleton.INSTANCE.getInstance());
    }
}
