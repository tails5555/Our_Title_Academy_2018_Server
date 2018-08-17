package io.kang.vo;

import io.kang.dto.mysql.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BriefFetchRequestVO {
    private Long id;
    private String userId;
    private Long categoryId;
    private String categoryName;
    private String bestTitle;
    private String intro;
    private String context;
    private long commentCount;
    private long titleCount;
    private long likeCount;
    private LocalDateTime writtenDate;

    public static BriefFetchRequestVO builtToVO(RequestDTO requestDTO, String bestTitle, long commentCount, long titleCount, long likeCount){
        return new BriefFetchRequestVO(requestDTO.getId(), requestDTO.getUserId(), (requestDTO.getCategory() != null) ? requestDTO.getCategory().getId() : -1L, (requestDTO.getCategory() != null) ? requestDTO.getCategory().getName() : "",  bestTitle, requestDTO.getIntro(), requestDTO.getContext(), commentCount, titleCount, likeCount, requestDTO.getWrittenDate());
    }
}
