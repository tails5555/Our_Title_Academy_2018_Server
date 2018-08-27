package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.TitleDTO;

import java.time.LocalDateTime;

public enum TitleDTOCreateSingleton {
    INSTANCE;
    private TitleDTO titleDTO = new TitleDTO(0L, RequestDTOUpdateSingleton.INSTANCE.getInstance(), "TITLE_USER_ID01", "TITLE_CONTEXT_01", LocalDateTime.MIN);
    public TitleDTO getInstance(){
        if(this.titleDTO == null)
            return new TitleDTO(0L, RequestDTOUpdateSingleton.INSTANCE.getInstance(), "TITLE_USER_ID01", "TITLE_CONTEXT_01", LocalDateTime.MIN);
        return this.titleDTO;
    }
}
