package io.kang.model;

import io.kang.vo.CityVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityModel {
    private Long id;
    private String name;
    public static CityModel builtToModel(CityVO cityVO){
        return new CityModel(cityVO.getId(), cityVO.getName());
    }
    public static CityVO builtToVO(CityModel cityModel){
        return new CityVO(cityModel.getId(), cityModel.getName());
    }
}
