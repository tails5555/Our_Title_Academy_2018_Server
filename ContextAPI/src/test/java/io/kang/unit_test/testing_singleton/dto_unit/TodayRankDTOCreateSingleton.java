package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.redis.TodayRankDTO;

public enum TodayRankDTOCreateSingleton {
    INSTANCE;
    private TodayRankDTO todayRankDTO = new TodayRankDTO(null, 1L, 1L, 1.0, 1.0);
    public TodayRankDTO getInstance(){
        if(this.todayRankDTO == null)
            return new TodayRankDTO(null, 1L, 1L, 1.0, 1.0);
        else return this.todayRankDTO;
    }
}
