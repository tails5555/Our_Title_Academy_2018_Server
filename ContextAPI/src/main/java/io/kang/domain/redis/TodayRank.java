package io.kang.domain.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@RedisHash("todayRank")
public class TodayRank implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Long sequence;
    private Long requestId;
}
