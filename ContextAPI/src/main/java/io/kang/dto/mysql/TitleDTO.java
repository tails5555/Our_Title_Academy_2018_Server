package io.kang.dto.mysql;

import io.kang.domain.mysql.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class TitleDTO {
    private Long id;
    private RequestDTO request;
    private String userId;
    private String context;
    private LocalDateTime writtenDate;

    public static TitleDTO builtToDTO(Title title){
        return new TitleDTO(title.getId(), RequestDTO.builtToDTO(title.getRequest()), title.getUserId(), title.getContext(), title.getWrittenDate());
    }

    public static Title builtToDomain(TitleDTO titleDTO){
        return new Title(titleDTO.getId(), RequestDTO.builtToDomain(titleDTO.getRequest()), titleDTO.getUserId(), titleDTO.getContext(), titleDTO.getWrittenDate());
    }
}
