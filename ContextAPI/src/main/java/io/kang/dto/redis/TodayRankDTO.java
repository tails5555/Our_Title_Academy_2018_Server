package io.kang.dto.redis;

import io.kang.domain.redis.TodayRank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class TodayRankDTO {
    private Long id;
    private Long sequence;
    private Long requestId;
    private Double score;
    private Double difference;

    public static TodayRankDTO builtToDTO(TodayRank todayRank){
        return new TodayRankDTO(todayRank.getId(), todayRank.getSequence(), todayRank.getRequestId(), todayRank.getScore(), todayRank.getDifference());
    }

    public static TodayRank builtToDomain(TodayRankDTO todayRankDTO){
        return new TodayRank(todayRankDTO.getId(), todayRankDTO.getSequence(), todayRankDTO.getRequestId(), todayRankDTO.getScore(), todayRankDTO.getDifference());
    }
}
