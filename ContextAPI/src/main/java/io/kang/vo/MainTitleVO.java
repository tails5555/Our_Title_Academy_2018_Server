package io.kang.vo;

import io.kang.dto.mysql.TitleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class MainTitleVO {
    private Long id;
    private Long requestId;
    private String context;
    private String userId;
    private LocalDateTime writtenDate;
    private Long likeCount;
    private Long hateCount;
    private Boolean likeChecked;
    private Boolean hateChecked;

    public static MainTitleVO builtToVO(TitleDTO titleDTO, long likeCount, long hateCount, Boolean likeChecked, Boolean hateChecked){
        return new MainTitleVO(titleDTO.getId(), titleDTO.getRequest().getId(), titleDTO.getContext(), titleDTO.getUserId(), titleDTO.getWrittenDate(), likeCount, hateCount, likeChecked, hateChecked);
    }
}
