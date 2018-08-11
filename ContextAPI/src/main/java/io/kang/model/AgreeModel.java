package io.kang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreeModel {
    private Long requestId;
    private Boolean available;
    private Long categoryId;
}