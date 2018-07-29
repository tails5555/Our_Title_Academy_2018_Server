package io.kang.dto.mysql;

import io.kang.domain.mysql.TitleEmpathy;
import io.kang.enumeration.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TitleEmpathyDTO extends EmpathyDTO {
    private String userId;
    private TitleDTO title;

    public TitleEmpathyDTO(){
        super();
    }

    public TitleEmpathyDTO(Long empathyId, Status status, LocalDateTime empathyCheckedDate, String userId, TitleDTO title){
        super(empathyId, status, empathyCheckedDate);
        this.userId = userId;
        this.title = title;
    }

    public static TitleEmpathyDTO builtToDTO(TitleEmpathy titleEmpathy){
        return new TitleEmpathyDTO(titleEmpathy.getId(), titleEmpathy.getStatus(), titleEmpathy.getCheckedDate(), titleEmpathy.getUserId(), TitleDTO.builtToDTO(titleEmpathy.getTitle()));
    }

    public static TitleEmpathy builtToDomain(TitleEmpathyDTO titleEmpathyDTO){
        return new TitleEmpathy(titleEmpathyDTO.getId(), titleEmpathyDTO.getStatus(), titleEmpathyDTO.getCheckedDate(), titleEmpathyDTO.getUserId(), TitleDTO.builtToDomain(titleEmpathyDTO.getTitle()));
    }
}
