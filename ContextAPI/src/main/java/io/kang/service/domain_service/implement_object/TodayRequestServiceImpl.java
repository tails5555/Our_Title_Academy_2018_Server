package io.kang.service.domain_service.implement_object;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kang.domain.redis.TodayRequest;
import io.kang.dto.redis.TodayRequestDTO;
import io.kang.repository.redis.TodayRequestRepository;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class TodayRequestServiceImpl implements TodayRequestService {
    @Autowired
    private TodayRequestRepository todayRequestRepository;

    @Override
    public void create(TodayRequestDTO todayRequestDTO) throws JsonProcessingException {
        todayRequestRepository.create(TodayRequestDTO.builtToDomain(todayRequestDTO));
    }

    @Override
    public TodayRequestDTO findByLast() throws IOException {
        TodayRequest todayRequest = todayRequestRepository.findByLast();
        if(todayRequest != null) return TodayRequestDTO.builtToDTO(todayRequest);
        else return null;
    }

    @Override
    public List<TodayRequestDTO> findAll() {
        return todayRequestRepository.findAll().stream()
                .map(todayRequest -> TodayRequestDTO.builtToDTO(todayRequest))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByFirst() {
        todayRequestRepository.deleteByFirst();
    }

    @Override
    public long count() {
        return todayRequestRepository.count();
    }
}
