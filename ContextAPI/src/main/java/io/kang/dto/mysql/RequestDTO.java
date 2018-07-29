package io.kang.dto.mysql;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RequestDTO {
    private Long id;
    private CategoryDTO category;
    private String userId;
    private String intro;
    private String context;
    private Boolean available;
    private LocalDateTime writtenDate;
    private Integer view;

    public static RequestDTO builtToDTO(Request request){
        CategoryDTO category = null;
        if(request.getCategory() != null){
            category = CategoryDTO.builtToDTO(request.getCategory());
            return new RequestDTO(request.getId(), category, request.getUserId(), request.getIntro(), request.getContext(), request.getAvailable(), request.getWrittenDate(), request.getView());
        } else return new RequestDTO(request.getId(), null, request.getUserId(), request.getIntro(), request.getContext(), request.getAvailable(), request.getWrittenDate(), request.getView());
    }

    public static Request builtToDomain(RequestDTO requestDTO){
        Category category = null;
        if(requestDTO.getCategory() != null){
            category = CategoryDTO.builtToDomain(requestDTO.getCategory());
            return new Request(requestDTO.getId(), category, requestDTO.getUserId(), requestDTO.getIntro(), requestDTO.getContext(), requestDTO.getAvailable(), requestDTO.getWrittenDate(), requestDTO.getView());
        } else return new Request(requestDTO.getId(), null, requestDTO.getUserId(), requestDTO.getIntro(), requestDTO.getContext(), requestDTO.getAvailable(), requestDTO.getWrittenDate(), requestDTO.getView());
    }
}
