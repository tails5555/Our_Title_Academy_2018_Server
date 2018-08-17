package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.redis.TodayRequest;

import java.time.LocalDateTime;

public enum TodayRequestTempSingleton {
    INSTANCE;
    private TodayRequest todayRequest = new TodayRequest(1L, LocalDateTime.MIN);
    public TodayRequest getInstance(){
        if(this.todayRequest == null){
            return new TodayRequest(1L, LocalDateTime.MIN);
        } else return this.todayRequest;
    }
}
