package io.kang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class BattleSocketVO {
    private Long titleId;
    private String userId;
    private List<MainTitleVO> titles;
    private Boolean hasTitle;
    private String context;

    public static BattleSocketVO builtToVO(Long titleId, String userId, List<MainTitleVO> titles, Boolean hasTitle, String context){
        return new BattleSocketVO(titleId, userId, titles, hasTitle, context);
    }
}
