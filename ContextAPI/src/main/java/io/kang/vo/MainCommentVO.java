package io.kang.vo;

import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.TitleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class MainCommentVO {
    private Long id;
    private String context;
    private String userId;
    private LocalDateTime writtenDate;
    private Long likeCount;
    private Long hateCount;
    private Boolean likeChecked;
    private Boolean hateChecked;

    public static MainCommentVO builtToVO(CommentDTO commentDTO, long likeCount, long hateCount, Boolean likeChecked, Boolean hateChecked){
        return new MainCommentVO(commentDTO.getId(), commentDTO.getContext(), commentDTO.getUserId(), commentDTO.getWrittenDate(), likeCount, hateCount, likeChecked, hateChecked);
    }
}
