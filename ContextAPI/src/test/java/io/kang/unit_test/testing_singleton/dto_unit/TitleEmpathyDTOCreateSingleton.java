package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum TitleEmpathyDTOCreateSingleton {
    INSTANCE;
    private TitleEmpathyDTO titleEmpathyDTO = new TitleEmpathyDTO(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance());
    public TitleEmpathyDTO getInstance(){
        if(this.titleEmpathyDTO == null)
            return new TitleEmpathyDTO(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance());
        else return this.titleEmpathyDTO;
    }
}
