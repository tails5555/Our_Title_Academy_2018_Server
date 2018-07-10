package io.kang.unit_test.value_object_unit;

import io.kang.domain.Profile;
import io.kang.unit_test.singleton_object.repository_testing.ProfileUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.ProfileVOSingleton;
import io.kang.vo.ProfileVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProfileVOUnitTest {
    private static Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
    private static ProfileVO profileVO = ProfileVOSingleton.INSTANCE.getInstance();

    @Before
    public void setup(){
        LocalDateTime current = LocalDateTime.now();
        this.profile.setUploadDate(current);
        this.profileVO.setUploadDate(current);
    }

    @Test
    public void built_to_vo_test() throws IOException {
        ProfileVO profileVO = ProfileVO.builtToVO(this.profile);
        Assert.assertEquals(profileVO, this.profileVO);
    }

    @Test
    public void built_to_domain_test() throws IOException{
        Profile profile = ProfileVO.builtToDomain(this.profileVO);
        Assert.assertEquals(profile, this.profile);
    }
}
