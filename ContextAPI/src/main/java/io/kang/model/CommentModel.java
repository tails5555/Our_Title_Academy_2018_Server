package io.kang.model;

import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    private Long commentId;
    private String userId;
    private Long requestId;
    private String context;
    public static CommentDTO builtToDTO(CommentModel commentModel, RequestDTO requestDTO){
        return new CommentDTO(commentModel.getCommentId(), commentModel.getUserId(), requestDTO, commentModel.getContext(), LocalDateTime.now());
    }
    public static CommentModel builtToModelIsExisted(CommentDTO commentDTO){
        return new CommentModel(commentDTO.getId(), commentDTO.getUserId(), commentDTO.getRequest().getId(), commentDTO.getContext());
    }
}
