package io.kang.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kang.domain.User;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class UserVO {
    private Long id;

    private String loginId;

    private String nickname;

    @JsonIgnore
    private String password;

    private Type userType;

    public static UserVO builtToVO(User user){
        UserVO userVO = new UserVO(user.getId(), user.getLoginId(), user.getNickname(), user.getPassword(), user.getUserType());
        return userVO;
    }

    public static User builtToDomain(UserVO userVO){
        User user = new User(userVO.getId(), userVO.getLoginId(), userVO.getNickname(), userVO.getPassword(), userVO.getUserType());
        return user;
    }
}
