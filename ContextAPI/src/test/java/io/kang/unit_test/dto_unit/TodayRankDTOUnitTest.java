package io.kang.unit_test.dto_unit;

import io.kang.domain.redis.TodayRank;
import io.kang.dto.redis.TodayRankDTO;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRankUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRankDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class TodayRankDTOUnitTest {
    private static final TodayRank todayRank = TodayRankUpdateSingleton.INSTANCE.getInstance();
    private static final TodayRankDTO todayRankDTO = TodayRankDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        TodayRankDTO todayRankDTO = TodayRankDTO.builtToDTO(this.todayRank);
        Assert.assertEquals(todayRankDTO, this.todayRankDTO);
    }

    @Test
    public void built_to_domain_test(){
        TodayRank todayRank = TodayRankDTO.builtToDomain(this.todayRankDTO);
        Assert.assertEquals(todayRank, this.todayRank);
    }
}
