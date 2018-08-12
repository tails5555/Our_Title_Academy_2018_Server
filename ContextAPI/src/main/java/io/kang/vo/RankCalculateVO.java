package io.kang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RankCalculateVO implements Comparable<RankCalculateVO> {
    private Long requestId;
    private Double score;
    private Double difference;

    @Override
    public int compareTo(RankCalculateVO rankCalculateVO){
        return Double.compare(rankCalculateVO.score, this.score);
    }

    public static RankCalculateVO builtToVO(Long requestId, Double score, Double difference){
        return new RankCalculateVO(requestId, score, difference);
    }
}
