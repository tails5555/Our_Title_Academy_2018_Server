package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.redis.TodayRequest;

import java.time.LocalDateTime;

public enum TodayRequestCreateSingleton {
    INSTANCE;
    private TodayRequest todayRequest = new TodayRequest(null, 1L, LocalDateTime.MIN);
    public TodayRequest getInstance(){
        if(this.todayRequest == null){
            return new TodayRequest(null, 1L, LocalDateTime.MIN);
        } else return this.todayRequest;
    }
}
