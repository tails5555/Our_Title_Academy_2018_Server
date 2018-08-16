package io.kang.vo;

import io.kang.dto.redis.TodayRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BattleVO {
    private Long requestId;
    private LocalDateTime selectDate;

    public static BattleVO builtToVO(Long requestId, LocalDateTime selectDate){
        return new BattleVO(requestId, selectDate);
    }

    public static TodayRequestDTO builtToCreateDTO(BattleVO battleVO){
        return new TodayRequestDTO(0L, battleVO.getRequestId(), battleVO.getSelectDate());
    }
}
