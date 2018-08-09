package io.kang.model;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TitleModel {
    private String userId;
    private Long requestId;
    private String context;
    public static TitleDTO builtToDTO(RequestDTO request, TitleModel titleModel){
        if(request != null)
            return new TitleDTO(0L, request, titleModel.getUserId(), titleModel.getContext(), LocalDateTime.now());
        else return null;
    }
    public static TitleDTO builtToDTOIsExisted(Long id, RequestDTO request, TitleModel titleModel, LocalDateTime writtenDate){
        if(request != null)
            return new TitleDTO(id, request, titleModel.getUserId(), titleModel.getContext(), writtenDate);
        else return null;
    }
    public static TitleModel builtToModelIsExisted(TitleDTO titleDTO){
        RequestDTO requestDTO = titleDTO.getRequest();
        if(requestDTO == null) return null;
        else return new TitleModel(titleDTO.getUserId(), requestDTO.getId(), titleDTO.getContext());
    }
}
