package io.kang.vo;

import io.kang.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class CityVO {
    private Long id;
    private String name;

    public static CityVO builtToVO(City city){
        CityVO cityVO = new CityVO(city.getId(), city.getName());
        return cityVO;
    }

    public static City builtToDomain(CityVO cityVO){
        City city = new City(cityVO.getId(), cityVO.getName());
        return city;
    }
}
