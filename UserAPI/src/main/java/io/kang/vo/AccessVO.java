package io.kang.vo;

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

    public static AccessVO currentAccessVO(UserVO userVO, LocalDateTime accessTime){
        if(userVO == null) return null;
        else return new AccessVO(userVO.getLoginId(), userVO.getNickname(), userVO.getUserType(), accessTime);
    }


}
