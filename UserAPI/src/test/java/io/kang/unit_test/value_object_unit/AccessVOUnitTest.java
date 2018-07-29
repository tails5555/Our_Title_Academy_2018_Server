package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.singleton_object.dto_unit.UserDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.vo_unit.AccessVOSingleton;
import io.kang.vo.AccessVO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class AccessVOUnitTest {
    @Test
    public void current_access_vo_test(){
        AccessVO accessVO = AccessVO.currentAccessVO(UserDTOUpdateSingleton.INSTANCE.getInstance(), LocalDateTime.MIN);
        Assert.assertEquals(accessVO, AccessVOSingleton.INSTANCE.getInstance());
    }
}
