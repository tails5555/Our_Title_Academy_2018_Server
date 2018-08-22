package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum RequestEmpathyDTOCreateSingleton {
    INSTANCE;
    private RequestEmpathyDTO requestEmpathyDTO = new RequestEmpathyDTO(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance());
    public RequestEmpathyDTO getInstance(){
        if(this.requestEmpathyDTO == null)
            return new RequestEmpathyDTO(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance());
        else return this.requestEmpathyDTO;
    }
}
