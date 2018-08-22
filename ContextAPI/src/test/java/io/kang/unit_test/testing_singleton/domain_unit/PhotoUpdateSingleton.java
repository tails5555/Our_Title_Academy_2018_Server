package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.Photo;
import io.kang.enumeration.Suffix;

import java.time.LocalDateTime;

public enum PhotoUpdateSingleton {
    INSTANCE;
    private Photo photo = new Photo(1L, "PHOTO_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), "PHOTO_FILE_NAME01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
    public Photo getInstance(){
        if(this.photo == null)
            return new Photo(1L, "PHOTO_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), "PHOTO_FILE_NAME01", 0, new byte[0], Suffix.PNG, LocalDateTime.MIN);
        return this.photo;
    }
}
