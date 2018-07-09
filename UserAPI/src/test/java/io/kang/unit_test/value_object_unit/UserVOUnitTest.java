package io.kang.unit_test.value_object_unit;

import io.kang.domain.User;
import io.kang.unit_test.singleton_object.repository_testing.UserUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.UserVOSingleton;
import io.kang.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UserVOUnitTest {
    private static User user = UserUpdateSingleton.INSTANCE.getInstance();
    private static UserVO userVO = UserVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_vo_test() throws IOException {
        UserVO userVO = UserVO.builtToVO(this.user);
        Assert.assertEquals(userVO, this.userVO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        User user = UserVO.builtToDomain(this.userVO);
        Assert.assertEquals(user, this.user);
    }
}
