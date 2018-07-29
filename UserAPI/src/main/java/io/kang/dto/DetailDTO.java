package io.kang.dto;

import io.kang.domain.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class DetailDTO {
    private Long id;
    private UserDTO user;
    private String name;
    private String email;
    private String homeNumber;
    private String phoneNumber;
    private CityDTO city;
    private AgeDTO age;
    public static DetailDTO builtToDTO(Detail detail){
        DetailDTO detailDTO = new DetailDTO(detail.getId(), UserDTO.builtToDTO(detail.getUser()), detail.getName(), detail.getEmail(), detail.getHomeNumber(), detail.getPhoneNumber(), CityDTO.builtToDTO(detail.getCity()), AgeDTO.builtToDTO(detail.getAge()));
        return detailDTO;
    }
    public static Detail builtToDomain(DetailDTO detailDTO){
        Detail detail = new Detail(detailDTO.getId(), UserDTO.builtToDomain(detailDTO.getUser()), detailDTO.getName(), detailDTO.getEmail(), detailDTO.getHomeNumber(), detailDTO.getPhoneNumber(), CityDTO.builtToDomain(detailDTO.getCity()), AgeDTO.builtToDomain(detailDTO.getAge()));
        return detail;
    }
}
