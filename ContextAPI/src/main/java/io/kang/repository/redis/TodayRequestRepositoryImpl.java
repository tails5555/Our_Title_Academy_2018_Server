package io.kang.repository.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.kang.domain.redis.TodayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TodayRequestRepositoryImpl implements TodayRequestRepository {
    private RedisTemplate<String, String> redisTemplate;
    private static final String TODAY_REQUEST_KEY = "TodayRequest";

    @Autowired
    public TodayRequestRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String builtToJSON(TodayRequest todayRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JSR310Module())
                .build();
        return objectMapper.writeValueAsString(todayRequest);
    }

    private TodayRequest builtToDomain(String json) throws IOException {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JSR310Module())
                .build();
        return objectMapper.readValue(json, TodayRequest.class);
    }

    @Override
    public void create(TodayRequest todayRequest) throws JsonProcessingException {
        this.redisTemplate.opsForList().rightPush(TODAY_REQUEST_KEY, this.builtToJSON(todayRequest));
    }

    @Override
    public TodayRequest findByLast() throws IOException {
        long currentSize = this.redisTemplate.opsForList().size(TODAY_REQUEST_KEY);
        String resultJSON = this.redisTemplate.opsForList().index(TODAY_REQUEST_KEY, currentSize - 1);
        if(resultJSON != null)
            return this.builtToDomain(resultJSON);
        else return null;
    }

    @Override
    public List<TodayRequest> findAll() {
        return this.redisTemplate.opsForList().range(TODAY_REQUEST_KEY, 0, -1).stream()
                .map(json -> {
                    try {
                        return this.builtToDomain(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(out -> out != null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByFirst() {
        this.redisTemplate.opsForList().leftPop(TODAY_REQUEST_KEY);
    }

    @Override
    public long count() {
        return this.redisTemplate.opsForList().size(TODAY_REQUEST_KEY);
    }
}
