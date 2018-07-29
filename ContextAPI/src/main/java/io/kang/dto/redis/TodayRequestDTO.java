package io.kang.dto.redis;

import io.kang.domain.redis.TodayRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class TodayRequestDTO {
    private Long id;
    private Long requestId;
    private LocalDateTime selectDate;

    public static TodayRequestDTO builtToDTO(TodayRequest todayRequest){
        return new TodayRequestDTO(todayRequest.getId(), todayRequest.getRequestId(), todayRequest.getSelectDate());
    }

    public static TodayRequest builtToDomain(TodayRequestDTO todayRequestDTO){
        return new TodayRequest(todayRequestDTO.getId(), todayRequestDTO.getRequestId(), todayRequestDTO.getSelectDate());
    }
}
