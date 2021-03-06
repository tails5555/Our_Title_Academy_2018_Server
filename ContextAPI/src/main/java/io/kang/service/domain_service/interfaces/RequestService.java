package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.model.OptionModel;
import io.kang.model.PaginationModel;

import java.util.List;

public interface RequestService {
    public List<OptionModel> getSearchByModel();
    public List<OptionModel> getOrderByModel();
    public List<Integer> getSizeByModel();
    public List<RequestDTO> findAll(final PaginationModel paginationModel);
    public List<RequestDTO> findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc();
    public List<RequestDTO> findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc();
    public List<RequestDTO> findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc(final String userId);
    public List<RequestDTO> findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(final String userId);
    public List<RequestDTO> findByUserIdAndCategoryOrderByWrittenDateDesc(final String userId, final CategoryDTO categoryDTO);
    public List<RequestDTO> findByCategoryIsNotNullAndAvailableIsTrue();
    public List<RequestDTO> findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc();
    public List<RequestDTO> findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc();
    public List<RequestDTO> findByIntroContainsOrContextContains(final String keyword);
    public RequestDTO getOne(final Long id);
    public RequestDTO findById(final Long id);
    public RequestDTO create(final RequestDTO requestDTO);
    public RequestDTO update(final RequestDTO requestDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
