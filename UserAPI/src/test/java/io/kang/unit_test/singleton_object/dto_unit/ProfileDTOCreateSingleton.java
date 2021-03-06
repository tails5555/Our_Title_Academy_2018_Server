package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.ProfileDTO;
import io.kang.enumeration.Suffix;

import java.time.LocalDateTime;

public enum ProfileDTOCreateSingleton {
    INSTANCE;
    private ProfileDTO profileDTO = new ProfileDTO(null, UserDTOUpdateSingleton.INSTANCE.getInstance(), "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
    public ProfileDTO getInstance(){
        if(this.profileDTO == null)
            return new ProfileDTO(null, UserDTOUpdateSingleton.INSTANCE.getInstance(), "PROFILE_FILE_NAME_01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
        else return this.profileDTO;
    }
}
