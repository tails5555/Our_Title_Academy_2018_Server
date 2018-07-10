package io.kang.service.domain_service.interfaces;

import io.kang.model.AgeModel;
import io.kang.vo.AgeVO;

import java.util.List;

public interface AgeService {
    public List<AgeVO> findAll();
    public AgeVO getOneVO(final Long id);
    public AgeVO findByIdVO(final Long id);
    public AgeModel getOneModel(final Long id);
    public AgeModel findByIdModel(final Long id);
    public AgeVO create(final AgeModel ageModel);
    public AgeVO update(final AgeModel ageModel);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
