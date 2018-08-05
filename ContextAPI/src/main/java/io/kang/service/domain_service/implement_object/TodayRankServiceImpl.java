package io.kang.service.domain_service.implement_object;

import io.kang.domain.redis.TodayRank;
import io.kang.dto.redis.TodayRankDTO;
import io.kang.repository.redis.TodayRankRepository;
import io.kang.service.domain_service.interfaces.TodayRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TodayRankServiceImpl implements TodayRankService {
    @Autowired
    private TodayRankRepository todayRankRepository;

    @Override
    public List<TodayRankDTO> findAll() {
        return StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .map(todayRank -> TodayRankDTO.builtToDTO(todayRank))
                .collect(Collectors.toList());
    }

    @Override
    public TodayRankDTO findById(final Long id) {
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(id);
        if(tmpTodayRank.isPresent()) return TodayRankDTO.builtToDTO(tmpTodayRank.get());
        else return null;
    }

    @Override
    public TodayRankDTO findByRequestId(Long requestId) {
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findByRequestId(requestId);
        if(tmpTodayRank.isPresent()) return TodayRankDTO.builtToDTO(tmpTodayRank.get());
        else return null;
    }

    @Override
    public TodayRankDTO create(final TodayRankDTO todayRankDTO) {
        TodayRank createTodayRank = todayRankRepository.save(TodayRankDTO.builtToDomain(todayRankDTO));
        if(createTodayRank.getId() != null) return TodayRankDTO.builtToDTO(createTodayRank);
        else return null;
    }

    @Override
    public TodayRankDTO update(final TodayRankDTO todayRankDTO) {
        TodayRank updateTodayRank = todayRankRepository.save(TodayRankDTO.builtToDomain(todayRankDTO));
        return TodayRankDTO.builtToDTO(updateTodayRank);
    }

    @Override
    public void deleteById(final Long id) {
        todayRankRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        todayRankRepository.deleteAll();
    }

    @Override
    public boolean existsById(final Long id) {
        return todayRankRepository.existsById(id);
    }

    @Override
    public long count() {
        return todayRankRepository.count();
    }
}
