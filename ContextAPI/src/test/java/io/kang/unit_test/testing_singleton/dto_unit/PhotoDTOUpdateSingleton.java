package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.PhotoDTO;
import io.kang.enumeration.Suffix;

import java.time.LocalDateTime;

public enum PhotoDTOUpdateSingleton {
    INSTANCE;
    private PhotoDTO photoDTO = new PhotoDTO(1L, "PHOTO_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), "PHOTO_FILE_NAME01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
    public PhotoDTO getInstance(){
        if(this.photoDTO == null)
            return new PhotoDTO(1L, "PHOTO_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), "PHOTO_FILE_NAME01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
        return this.photoDTO;
    }
}
