package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.redis.TodayRequestDTO;

import java.time.LocalDateTime;

public enum TodayRequestDTOTempSingleton {
    INSTANCE;
    private TodayRequestDTO todayRequestDTO = new TodayRequestDTO(1L, LocalDateTime.MIN);
    public TodayRequestDTO getInstance(){
        if(this.todayRequestDTO == null){
            return new TodayRequestDTO(1L, LocalDateTime.MIN);
        } else return this.todayRequestDTO;
    }
}
