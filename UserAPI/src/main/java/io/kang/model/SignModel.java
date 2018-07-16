package io.kang.model;

import io.kang.enumeration.Type;
import io.kang.vo.AgeVO;
import io.kang.vo.CityVO;
import io.kang.vo.DetailVO;
import io.kang.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignModel {
    private String loginId;
    private String mainPassword;
    private String subPassword;
    private String name;
    private String nickname;
    private String email;
    private String homeNumber;
    private String phoneNumber;
    private Long ageId;
    private Long cityId;
    public boolean isPasswordEquals(){
        return this.mainPassword.equals(this.subPassword);
    }
    public static UserVO builtToUserVO(SignModel signModel){
        return new UserVO(null, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), Type.USER);
    }
    public static DetailVO builtToDetailVO(SignModel signModel, UserVO userVO, AgeVO ageVO, CityVO cityVO){
        return new DetailVO(null, userVO, signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), cityVO, ageVO);
    }
    public static SignModel builtToSignModel(DetailVO detailVO, AgeVO ageVO, CityVO cityVO){
        UserVO userVO = detailVO.getUser();
        if(userVO != null){
            return new SignModel(userVO.getLoginId(), "", "", detailVO.getName(), userVO.getNickname(), detailVO.getEmail(), detailVO.getHomeNumber(), detailVO.getPhoneNumber(), ageVO.getId(), cityVO.getId());
        } else return null;
    }
}
