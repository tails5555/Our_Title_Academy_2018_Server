package io.kang.vo;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class SearchResultVO {
    private Long requestId;
    private String type;
    private Long categoryId;
    private String categotyName;
    private String intro;
    private String context;
    private long likeCount;
    private long hateCount;

    public static SearchResultVO builtToVOWithTitleDTO(TitleDTO titleDTO, long likeCount, long hateCount){
        RequestDTO requestDTO = titleDTO.getRequest();
        CategoryDTO categoryDTO = requestDTO.getCategory();
        if(requestDTO.getAvailable() && categoryDTO != null)
            return new SearchResultVO(requestDTO.getId(), Type.TITLE, categoryDTO.getId(), categoryDTO.getName(), requestDTO.getIntro(), titleDTO.getContext(), likeCount, hateCount);
        else return null;
    }

    public static SearchResultVO builtToVOWithRequestDTO(RequestDTO requestDTO, long likeCount, long hateCount){
        if(requestDTO.getAvailable() && requestDTO.getCategory() != null) {
            CategoryDTO categoryDTO = requestDTO.getCategory();
            String tmpContext = requestDTO.getContext().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " ");
            return new SearchResultVO(requestDTO.getId(), Type.REQUEST, categoryDTO.getId(), categoryDTO.getName(), requestDTO.getIntro(), tmpContext, likeCount, hateCount);
        }
        else return null;
    }
}
