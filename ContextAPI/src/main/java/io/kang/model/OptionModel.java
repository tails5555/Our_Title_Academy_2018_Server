package io.kang.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionModel {
    private Long value;
    private String label;
}
