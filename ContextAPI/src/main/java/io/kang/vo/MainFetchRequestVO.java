package io.kang.vo;

import io.kang.dto.mysql.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class MainFetchRequestVO {
    private RequestDTO requestDTO;
    private List<BestTitleVO> bestTitles;
    private Long likeCount;
    private Long hateCount;
    private Boolean likeChecked;
    private Boolean hateChecked;

    public static MainFetchRequestVO builtToVO(RequestDTO requestDTO, List<BestTitleVO> bestTitles, long likeCount, long hateCount, Boolean likeChecked, Boolean hateChecked){
        return new MainFetchRequestVO(requestDTO, bestTitles, likeCount, hateCount, likeChecked, hateChecked);
    }
}
