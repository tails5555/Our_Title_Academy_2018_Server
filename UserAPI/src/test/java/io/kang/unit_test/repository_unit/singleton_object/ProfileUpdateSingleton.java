package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.Profile;
import io.kang.domain.User;
import io.kang.enumeration.Suffix;

import java.time.LocalDateTime;

public enum ProfileUpdateSingleton {
    INSTANCE;
    private User user = UserUpdateSingleton.INSTANCE.getInstance();
    private Profile profile = new Profile(1L, this.user, "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.now());
    public Profile getInstance(){
        if(this.user == null){
            this.user = UserUpdateSingleton.INSTANCE.getInstance();
        }
        if(this.profile == null){
            return new Profile(1L, this.user, "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.now());
        } else return this.profile;
    }
}
