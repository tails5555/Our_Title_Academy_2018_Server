package io.kang.vo;

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
}
