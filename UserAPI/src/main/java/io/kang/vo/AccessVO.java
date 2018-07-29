package io.kang.vo;

import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class AccessVO {
    private String loginId;
    private String nickname;
    private Type type;
    private LocalDateTime accessTime;

    public static AccessVO currentAccessVO(UserDTO userDTO, LocalDateTime accessTime){
        if(userDTO == null) return null;
        else return new AccessVO(userDTO.getLoginId(), userDTO.getNickname(), userDTO.getUserType(), accessTime);
    }
}
