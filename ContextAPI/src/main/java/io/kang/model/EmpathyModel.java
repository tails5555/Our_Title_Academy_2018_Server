package io.kang.model;

import io.kang.enumeration.Status;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class EmpathyModel {
    private Long id;
    private Type type;
    private Status status;
    private Long contentId;
}
