package io.kang.service.domain_service.interfaces;

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
    public TitleDTO create(final TitleDTO titleDTO);
    public TitleDTO update(final TitleDTO titleDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
    public long countByRequest(RequestDTO requestDTO);
}
