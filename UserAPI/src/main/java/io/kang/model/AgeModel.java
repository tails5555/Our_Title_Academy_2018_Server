package io.kang.model;

import io.kang.vo.AgeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeModel {
    private Long id;
    private String name;
    public static AgeModel builtToModel(AgeVO ageVO){
        return new AgeModel(ageVO.getId(), ageVO.getName());
    }
    public static AgeVO builtToVO(AgeModel ageModel){
        return new AgeVO(ageModel.getId(), ageModel.getName());
    }
}
