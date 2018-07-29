package io.kang.dto.mysql;

import io.kang.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class EmpathyDTO {
    private Long id;
    private Status status;
    private LocalDateTime checkedDate;
}
