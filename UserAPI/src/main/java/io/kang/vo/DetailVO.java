package io.kang.vo;

import io.kang.domain.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class DetailVO {
    private Long id;
    private UserVO user;
    private String name;
    private String email;
    private String homeNumber;
    private String phoneNumber;
    private CityVO city;
    private AgeVO age;
    public static DetailVO builtToVO(Detail detail){
        DetailVO detailVO = new DetailVO(detail.getId(), UserVO.builtToVO(detail.getUser()), detail.getName(), detail.getEmail(), detail.getHomeNumber(), detail.getPhoneNumber(), CityVO.builtToVO(detail.getCity()), AgeVO.builtToVO(detail.getAge()));
        return detailVO;
    }
    public static Detail builtToDomain(DetailVO detailVO){
        Detail detail = new Detail(detailVO.getId(), UserVO.builtToDomain(detailVO.getUser()), detailVO.getName(), detailVO.getEmail(), detailVO.getHomeNumber(), detailVO.getPhoneNumber(), CityVO.builtToDomain(detailVO.getCity()), AgeVO.builtToDomain(detailVO.getAge()));
        return detail;
    }
}
