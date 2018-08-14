package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Request;
import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.model.OptionModel;
import io.kang.model.PaginationModel;
import io.kang.repository.mysql.RequestRepository;
import io.kang.service.domain_service.interfaces.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<OptionModel> getSearchByModel() {
        return requestRepository.searchBy;
    }

    @Override
    public List<OptionModel> getOrderByModel() {
        return requestRepository.orderBy;
    }

    @Override
    public List<Integer> getSizeByModel() {
        return requestRepository.sizeBy;
    }

    @Override
    public List<RequestDTO> findAll(PaginationModel paginationModel) {
        return requestRepository.findAll(paginationModel).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findTop10ByCategoryIsNotNullAndAvailableOrderByWrittenDateDesc(final Boolean available) {
        return requestRepository.findTop10ByCategoryIsNotNullAndAvailableOrderByWrittenDateDesc(available).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findTop10ByCategoryIsNotNullAndAvailableOrderByViewDesc(Boolean available) {
        return requestRepository.findTop10ByCategoryIsNotNullAndAvailableOrderByViewDesc(available).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc(final String userId) {
        return requestRepository.findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc(userId).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(final String userId) {
        return requestRepository.findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(userId).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByUserIdAndCategoryOrderByWrittenDateDesc(final String userId, final CategoryDTO categoryDTO) {
        return requestRepository.findByUserIdAndCategoryOrderByWrittenDateDesc(userId, CategoryDTO.builtToDomain(categoryDTO)).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByCategoryIsNullOrderByWrittenDateDesc() {
        return requestRepository.findByCategoryIsNullOrderByWrittenDateDesc().stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc() {
        return requestRepository.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc().stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByIntroContainsOrContextContains(String keyword) {
        return requestRepository.findByIntroContainsOrContextContains(keyword, keyword).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public RequestDTO getOne(final Long id) {
        Request request = requestRepository.getOne(id);
        if(request != null) return RequestDTO.builtToDTO(request);
        else return null;
    }

    @Override
    public RequestDTO findById(final Long id) {
        Optional<Request> tmpRequest = requestRepository.findById(id);
        if(tmpRequest.isPresent()) return RequestDTO.builtToDTO(tmpRequest.get());
        else return null;
    }

    @Override
    public RequestDTO create(final RequestDTO requestDTO) {
        Request createRequest = requestRepository.save(RequestDTO.builtToDomain(requestDTO));
        if(createRequest.getId() != null) return RequestDTO.builtToDTO(createRequest);
        else return null;
    }

    @Override
    public RequestDTO update(final RequestDTO requestDTO) {
        Request updateRequest = requestRepository.save(RequestDTO.builtToDomain(requestDTO));
        return RequestDTO.builtToDTO(updateRequest);
    }

    @Override
    public void deleteById(final Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return requestRepository.existsById(id);
    }

    @Override
    public long count() {
        return requestRepository.count();
    }
}
