package io.kang.vo;

import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class PrincipalVO {
    private String loginId;
    private String name;
    private String nickname;
    private Type type;

    public static PrincipalVO builtToVO(DetailDTO detailDTO){
        UserDTO userDTO = detailDTO.getUser();
        if(userDTO == null) return null;
        return new PrincipalVO(userDTO.getLoginId(), detailDTO.getName(), userDTO.getNickname(), userDTO.getUserType());
    }
}
