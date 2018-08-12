package io.kang.vo;

import io.kang.dto.mysql.TitleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BestTitleVO {
    private String userId;
    private String context;
    private long likeCount;
    public static BestTitleVO builtToVO(TitleDTO titleDTO, long likeCount){
        return new BestTitleVO(titleDTO.getUserId(), titleDTO.getContext(), likeCount);
    }
}
