package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.RequestDTO;

import java.util.List;

public interface CommentService {
    public List<CommentDTO> findAll();
    public List<CommentDTO> findByUserIdOrderByWrittenDateDesc(String userId);
    public List<CommentDTO> findByRequestOrderByWrittenDateDesc(RequestDTO requestDTO);
    public CommentDTO getOne(final Long id);
    public CommentDTO findById(final Long id);
    public CommentDTO create(final CommentDTO commentDTO);
    public CommentDTO update(final CommentDTO commentDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
    public long countByRequest(RequestDTO requestDTO);
}
