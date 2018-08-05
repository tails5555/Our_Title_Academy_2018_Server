package io.kang.service.domain_service.interfaces;

import io.kang.domain.mysql.Request;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;

import java.util.List;

public interface TitleService {
    public List<TitleDTO> findAll();
    public List<TitleDTO> findAllByOrderByWrittenDateDesc();
    public List<TitleDTO> findByUserIdOrderByWrittenDateDesc(final String userId);
    public List<TitleDTO> findByRequestOrderByWrittenDateDesc(final RequestDTO requestDTO);
    public TitleDTO getOne(final Long id);
    public TitleDTO findById(final Long id);
    public TitleDTO findTopByRequestIdOrderByLikeCountDesc(final Long requestId);
    public List<TitleDTO> findTop5ByRequestIdOrderByLikeCountDesc(final Long requestId);
    public TitleDTO findByUserIdAndRequest(final String userId, final RequestDTO requestDTO);
    public TitleDTO getRandomTitle(final RequestDTO requestDTO);
    public TitleDTO create(final TitleDTO titleDTO);
    public TitleDTO update(final TitleDTO titleDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public boolean existsByUserIdAndRequest(final String userId, final RequestDTO requestDTO);
    public long count();
    public long countByRequest(RequestDTO requestDTO);
}
