package io.kang.unit_test.singleton_object.value_object_testing;

import io.kang.enumeration.Suffix;
import io.kang.vo.ProfileVO;

import java.time.LocalDateTime;

public enum ProfileVOSingleton {
    INSTANCE;
    private ProfileVO profileVO = new ProfileVO(1L, UserVOSingleton.INSTANCE.getInstance(), "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.now());
    public ProfileVO getInstance(){
        if(this.profileVO == null)
            return new ProfileVO(1L, UserVOSingleton.INSTANCE.getInstance(), "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.now());
        else return this.profileVO;
    }
}
