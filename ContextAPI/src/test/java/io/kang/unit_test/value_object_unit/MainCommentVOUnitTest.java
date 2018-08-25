package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.MainCommentVOSingleton;
import io.kang.vo.MainCommentVO;
import org.junit.Assert;
import org.junit.Test;

public class MainCommentVOUnitTest {
    @Test
    public void built_to_vo_test(){
        MainCommentVO mainCommentVO = MainCommentVO.builtToVO(CommentDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L, false, false);
        Assert.assertEquals(mainCommentVO, MainCommentVOSingleton.INSTANCE.getInstance());
    }
}
