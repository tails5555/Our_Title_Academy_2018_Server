package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.RequestDTO;
import java.time.LocalDateTime;

public enum RequestDTOCreateSingleton {
    INSTANCE;
    private RequestDTO requestDTO = new RequestDTO(null, null, "REQUEST_USER_ID01", "REQUEST_INTRO_01", "REQUEST_CONTEXT_01", false, LocalDateTime.MIN, 0);
    public RequestDTO getInstance(){
        if(this.requestDTO == null)
            return new RequestDTO(null,null, "REQUEST_USER_ID01", "REQUEST_INTRO_01", "REQUEST_CONTEXT_01", false, LocalDateTime.MIN, 0);
        return this.requestDTO;
    }
}
