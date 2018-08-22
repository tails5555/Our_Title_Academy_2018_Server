package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.redis.TodayRankDTO;

public enum TodayRankDTOUpdateSingleton {
    INSTANCE;
    private TodayRankDTO todayRankDTO = new TodayRankDTO(1L, 1L, 1L, 1.0, 1.0);
    public TodayRankDTO getInstance(){
        if(this.todayRankDTO == null)
            return new TodayRankDTO(1L, 1L, 1L, 1.0, 1.0);
        else return this.todayRankDTO;
    }
}
