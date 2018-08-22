package io.kang.unit_test.dto_unit;

import io.kang.domain.redis.TodayRequest;
import io.kang.dto.redis.TodayRequestDTO;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRequestTempSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRequestDTOTempSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TodayRequestDTOUnitTest {
    private TodayRequest todayRequest = TodayRequestTempSingleton.INSTANCE.getInstance();
    private TodayRequestDTO todayRequestDTO = TodayRequestDTOTempSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        TodayRequestDTO todayRequestDTO = TodayRequestDTO.builtToDTO(this.todayRequest);
        Assert.assertEquals(todayRequestDTO, this.todayRequestDTO);
    }

    @Test
    public void built_to_domain_test(){
        TodayRequest todayRequest = TodayRequestDTO.builtToDomain(this.todayRequestDTO);
        Assert.assertEquals(todayRequest, this.todayRequest);
    }

    @Test
    public void built_to_dto_property_test(){
        TodayRequestDTO todayRequestDTO = TodayRequestDTO.builtToDTOProperty(1L, LocalDateTime.MIN);
        Assert.assertEquals(todayRequestDTO, this.todayRequestDTO);
    }
}
