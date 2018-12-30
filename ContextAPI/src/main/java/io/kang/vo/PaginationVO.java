package io.kang.vo;

import io.kang.model.PaginationModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationVO {
    private long count;
    private List<BriefFetchRequestVO> results;

    public static PaginationVO builtToVO(long count, List<BriefFetchRequestVO> briefFetchRequestVOList){
        return new PaginationVO(count, briefFetchRequestVOList);
    }
}
