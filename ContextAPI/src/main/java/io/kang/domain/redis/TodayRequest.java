package io.kang.domain.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@RedisHash("todayRequest")
public class TodayRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Long requestId;
    private LocalDateTime selectDate;
}
