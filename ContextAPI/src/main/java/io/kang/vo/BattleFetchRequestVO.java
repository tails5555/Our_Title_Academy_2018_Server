package io.kang.vo;

import io.kang.dto.mysql.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BattleFetchRequestVO {
    private RequestDTO requestDTO;
    private Long likeCount;
    private Long hateCount;
    private Boolean likeChecked;
    private Boolean hateChecked;

    public static BattleFetchRequestVO builtToVO(RequestDTO requestDTO, long likeCount, long hateCount, Boolean likeChecked, Boolean hateChecked){
        return new BattleFetchRequestVO(requestDTO, likeCount, hateCount, likeChecked, hateChecked);
    }
}
