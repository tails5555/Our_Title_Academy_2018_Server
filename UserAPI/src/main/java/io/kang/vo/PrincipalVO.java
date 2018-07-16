package io.kang.vo;

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

    public static PrincipalVO builtToVO(DetailVO detailVO){
        UserVO userVO = detailVO.getUser();
        if(userVO == null) return null;
        return new PrincipalVO(userVO.getLoginId(), detailVO.getName(), userVO.getNickname(), userVO.getUserType());
    }
}
