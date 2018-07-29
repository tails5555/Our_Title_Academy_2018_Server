package io.kang.dto;

import io.kang.domain.Age;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class AgeDTO {
    private Long id;
    private String name;

    public static AgeDTO builtToDTO(Age age){
        AgeDTO ageDTO = new AgeDTO(age.getId(), age.getName());
        return ageDTO;
    }

    public static Age builtToDomain(AgeDTO ageDTO){
        Age age = new Age(ageDTO.getId(), ageDTO.getName());
        return age;
    }
}
