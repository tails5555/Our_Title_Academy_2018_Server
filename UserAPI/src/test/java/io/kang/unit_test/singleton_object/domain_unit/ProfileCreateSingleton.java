package io.kang.unit_test.singleton_object.domain_unit;

import io.kang.domain.Profile;
import io.kang.domain.User;
import io.kang.enumeration.Suffix;

import java.time.LocalDateTime;

public enum ProfileCreateSingleton {
    INSTANCE;
    private User user = UserUpdateSingleton.INSTANCE.getInstance();
    private Profile profile = new Profile(null, this.user, "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
    public Profile getInstance(){
        if(this.user == null){
            this.user = UserUpdateSingleton.INSTANCE.getInstance();
        }
        if(this.profile == null){
            return new Profile(null, this.user, "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
        } else return this.profile;
    }
}
