package io.kang.service.domain_service.implement_object;

import io.kang.domain.redis.TodayRequest;
import io.kang.dto.redis.TodayRequestDTO;
import io.kang.repository.redis.TodayRequestRepository;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TodayRequestServiceImpl implements TodayRequestService {
    @Autowired
    private TodayRequestRepository todayRequestRepository;

    @Override
    public List<TodayRequestDTO> findAll() {
        return StreamSupport.stream(todayRequestRepository.findAll().spliterator(), false)
                .map(todayRequest -> TodayRequestDTO.builtToDTO(todayRequest))
                .collect(Collectors.toList());
    }

    @Override
    public TodayRequestDTO findById(final Long id) {
        Optional<TodayRequest> tmpTodayRequest = todayRequestRepository.findById(id);
        if(tmpTodayRequest.isPresent()) return TodayRequestDTO.builtToDTO(tmpTodayRequest.get());
        else return null;
    }

    @Override
    public TodayRequestDTO create(final TodayRequestDTO todayRequestDTO) {
        TodayRequest createTodayRequest = todayRequestRepository.save(TodayRequestDTO.builtToDomain(todayRequestDTO));
        if(createTodayRequest.getId() != null) return TodayRequestDTO.builtToDTO(createTodayRequest);
        else return null;
    }

    @Override
    public TodayRequestDTO update(final TodayRequestDTO todayRequestDTO) {
        TodayRequest updateTodayRequest = todayRequestRepository.save(TodayRequestDTO.builtToDomain(todayRequestDTO));
        return TodayRequestDTO.builtToDTO(updateTodayRequest);
    }

    @Override
    public void deleteById(final Long id) {
        todayRequestRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        todayRequestRepository.deleteAll();
    }

    @Override
    public boolean existsById(final Long id) {
        return todayRequestRepository.existsById(id);
    }

    @Override
    public boolean existsByRequestId(final Long requestId) {
        return existsByRequestId(requestId);
    }

    @Override
    public long count() {
        return todayRequestRepository.count();
    }
}
