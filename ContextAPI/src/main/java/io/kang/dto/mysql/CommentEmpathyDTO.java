package io.kang.dto.mysql;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.enumeration.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentEmpathyDTO extends EmpathyDTO {
    private String userId;
    private CommentDTO comment;

    public CommentEmpathyDTO(){
        super();
    }

    public CommentEmpathyDTO(Long empathyId, Status status, LocalDateTime empathyCheckedDate, String userId, CommentDTO comment){
        super(empathyId, status, empathyCheckedDate);
        this.userId = userId;
        this.comment = comment;
    }

    public static CommentEmpathyDTO builtToDTO(CommentEmpathy commentEmpathy){
        return new CommentEmpathyDTO(commentEmpathy.getId(), commentEmpathy.getStatus(), commentEmpathy.getCheckedDate(), commentEmpathy.getUserId(), CommentDTO.builtToDTO(commentEmpathy.getComment()));
    }

    public static CommentEmpathy builtToDomain(CommentEmpathyDTO commentEmpathyDTO){
        return new CommentEmpathy(commentEmpathyDTO.getId(), commentEmpathyDTO.getStatus(), commentEmpathyDTO.getCheckedDate(), commentEmpathyDTO.getUserId(), CommentDTO.builtToDomain(commentEmpathyDTO.getComment()));
    }
}
