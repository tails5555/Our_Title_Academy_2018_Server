package io.kang.model;

import io.kang.dto.mysql.CategoryDTO;
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
public class RequestModel {
    private String intro;
    private String userId;
    private String context;
    public static RequestDTO builtToDTO(RequestModel requestModel){
        return new RequestDTO(0L, null, requestModel.getUserId(), requestModel.getIntro(), requestModel.getContext(), false, LocalDateTime.now(), 0);
    }
    public static RequestDTO builtToDTOIsExisted(RequestDTO requestDTO, CategoryDTO categoryDTO, RequestModel requestModel){
        if(categoryDTO == null)
            return new RequestDTO(requestDTO.getId(), categoryDTO, requestModel.getUserId(), requestModel.getContext(), requestModel.getContext(), requestDTO.getAvailable(), requestDTO.getWrittenDate(), requestDTO.getView());
        else return new RequestDTO(requestDTO.getId(), null, requestModel.getUserId(), requestModel.getContext(), requestModel.getContext(), requestDTO.getAvailable(), requestDTO.getWrittenDate(), requestDTO.getView());
    }
    public static RequestModel builtToModelIsExisted(RequestDTO requestDTO){
        return new RequestModel(requestDTO.getIntro(), requestDTO.getUserId(), requestDTO.getContext());
    }
}
