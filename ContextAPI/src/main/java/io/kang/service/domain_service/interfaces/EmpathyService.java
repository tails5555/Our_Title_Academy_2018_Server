package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.EmpathyDTO;
import io.kang.enumeration.Status;

import java.util.List;

public interface EmpathyService<T extends EmpathyDTO, V> {
    public List<T> findAll();
    public T getOne(final Long id);
    public T findById(final Long id);
    public T create(final T baseDTO);
    public T update(final T baseDTO);
    public void deleteById(final Long id);
    public void deleteByUserIdAndContext(final String userId, final V context);
    public boolean existsById(final Long id);
    public boolean existsByUserIdAndContext(final String userId, final V context);
    public long count();
    public long countByContextAndStatus(final V context, final Status status);
}
