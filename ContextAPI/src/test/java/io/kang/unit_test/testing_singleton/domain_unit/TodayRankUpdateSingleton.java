package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.redis.TodayRank;

public enum TodayRankUpdateSingleton {
    INSTANCE;
    private TodayRank todayRank = new TodayRank(1L, 1L, 1L, 1.0);
    public TodayRank getInstance(){
        if(this.todayRank == null)
            return new TodayRank(1L, 1L, 1L, 1.0);
        else return this.todayRank;
    }
}
