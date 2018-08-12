package io.kang.vo;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.redis.TodayRankDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RankFetchRequestVO {
    private Long sequence;
    private Double difference;
    private Long requestId;
    private Long categoryId;
    private String categoryName;
    private String intro;
    private String bestTitle;
    private Long likeCount;
    private Long commentCount;
    public static RankFetchRequestVO builtToVO(RequestDTO requestDTO, TodayRankDTO todayRankDTO, TitleDTO titleDTO, long likeCount, long commentCount){
        return new RankFetchRequestVO(todayRankDTO.getSequence(), todayRankDTO.getDifference(), requestDTO.getId(), requestDTO.getCategory().getId(), requestDTO.getCategory().getName(), requestDTO.getIntro(), (titleDTO != null) ? titleDTO.getContext() : "여러분이 제목을 올려주세요.", likeCount, commentCount);
    }
}
