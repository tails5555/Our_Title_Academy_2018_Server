package io.kang.dto.mysql;

import io.kang.domain.mysql.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String userId;
    private RequestDTO request;
    private String context;
    private LocalDateTime writtenDate;
    public static CommentDTO builtToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getUserId(), RequestDTO.builtToDTO(comment.getRequest()), comment.getContext(), comment.getWrittenDate());
    }
    public static Comment builtToDomain(CommentDTO commentDTO){
        return new Comment(commentDTO.getId(), commentDTO.getUserId(), RequestDTO.builtToDomain(commentDTO.getRequest()), commentDTO.getContext(), commentDTO.getWrittenDate());
    }
}
