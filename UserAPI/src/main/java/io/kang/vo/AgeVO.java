package io.kang.vo;

import io.kang.domain.Age;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgeVO {
    private Long id;
    private String name;

    public static AgeVO builtToVO(Age age){
        AgeVO ageVO = new AgeVO(age.getId(), age.getName());
        return ageVO;
    }

    public static Age builtToDomain(AgeVO ageVO){
        Age age = new Age(ageVO.getId(), ageVO.getName());
        return age;
    }
}
