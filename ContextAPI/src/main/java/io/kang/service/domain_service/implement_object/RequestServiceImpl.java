package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Request;
import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
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
    public List<RequestDTO> findAll() {
        return requestRepository.findAll().stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findAllByOrderByWrittenDateDesc() {
        return requestRepository.findAllByOrderByWrittenDateDesc().stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(final String userId) {
        return requestRepository.findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(userId).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(final String userId) {
        return requestRepository.findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(userId).stream()
                .map(request -> RequestDTO.builtToDTO(request))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> findByCategoryOrderByWrittenDateDesc(final CategoryDTO categoryDTO) {
        return requestRepository.findByCategoryOrderByWrittenDateDesc(CategoryDTO.builtToDomain(categoryDTO)).stream()
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
