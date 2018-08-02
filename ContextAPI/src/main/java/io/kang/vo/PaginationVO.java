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
    private PaginationModel paginationModel;
    private List<BriefFetchRequestVO> briefFetchRequestVOList;

    public static PaginationVO builtToVO(PaginationModel paginationModel, List<BriefFetchRequestVO> briefFetchRequestVOList){
        return new PaginationVO(paginationModel, briefFetchRequestVOList);
    }
}
