package io.kang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContextStatisticVO {
    private String categoryName;
    private Long count;
    private Long totalStatus;

    public static ContextStatisticVO builtToVO(String categoryName, long count, long status){
        return new ContextStatisticVO(categoryName, count, status);
    }
}
