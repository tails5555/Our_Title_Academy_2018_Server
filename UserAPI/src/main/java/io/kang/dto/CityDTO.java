package io.kang.dto;

import io.kang.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class CityDTO {
    private Long id;
    private String name;

    public static CityDTO builtToDTO(City city){
        CityDTO cityDTO = new CityDTO(city.getId(), city.getName());
        return cityDTO;
    }

    public static City builtToDomain(CityDTO cityDTO){
        City city = new City(cityDTO.getId(), cityDTO.getName());
        return city;
    }
}
