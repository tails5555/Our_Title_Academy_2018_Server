package io.kang.vo;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
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
    private Long categoryId;
    private String categoryName;
    private String context;
    private String userId;
    private LocalDateTime writtenDate;
    private Long likeCount;
    private Long hateCount;
    private Boolean likeChecked;
    private Boolean hateChecked;

    public static MainTitleVO builtToVO(TitleDTO titleDTO, long likeCount, long hateCount, Boolean likeChecked, Boolean hateChecked){
        RequestDTO requestDTO = titleDTO.getRequest();
        CategoryDTO categoryDTO = requestDTO.getCategory();
        return new MainTitleVO(titleDTO.getId(), requestDTO.getId(), categoryDTO.getId(), categoryDTO.getName(), titleDTO.getContext(), titleDTO.getUserId(), titleDTO.getWrittenDate(), likeCount, hateCount, likeChecked, hateChecked);
    }
}
