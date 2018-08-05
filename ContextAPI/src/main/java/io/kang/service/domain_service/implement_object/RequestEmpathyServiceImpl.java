package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.RequestEmpathyRepository;
import io.kang.service.domain_service.interfaces.EmpathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestEmpathyServiceImpl implements EmpathyService<RequestEmpathyDTO, RequestDTO> {
    @Autowired
    private RequestEmpathyRepository requestEmpathyRepository;

    @Override
    public List<RequestEmpathyDTO> findAll() {
        return requestEmpathyRepository.findAll().stream()
                .map(requestEmpathy -> RequestEmpathyDTO.builtToDTO(requestEmpathy))
                .collect(Collectors.toList());
    }

    @Override
    public RequestEmpathyDTO getOne(final Long id) {
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(id);
        if(requestEmpathy != null) return RequestEmpathyDTO.builtToDTO(requestEmpathy);
        else return null;
    }

    @Override
    public RequestEmpathyDTO findById(final Long id) {
        Optional<RequestEmpathy> tmpRequestEmpathy = requestEmpathyRepository.findById(id);
        if(tmpRequestEmpathy.isPresent()) return RequestEmpathyDTO.builtToDTO(tmpRequestEmpathy.get());
        else return null;
    }

    @Override
    public RequestEmpathyDTO findByUserIdAndContext(final String userId, final RequestDTO context) {
        Optional<RequestEmpathy> tmpRequestEmpathy = requestEmpathyRepository.findByUserIdAndRequest(userId, RequestDTO.builtToDomain(context));
        if(tmpRequestEmpathy.isPresent()) return RequestEmpathyDTO.builtToDTO(tmpRequestEmpathy.get());
        else return null;
    }

    @Override
    public RequestEmpathyDTO create(final RequestEmpathyDTO baseDTO) {
        RequestEmpathy createRequestEmpathy = requestEmpathyRepository.save(RequestEmpathyDTO.builtToDomain(baseDTO));
        if(createRequestEmpathy.getId() != null) return RequestEmpathyDTO.builtToDTO(createRequestEmpathy);
        else return null;
    }

    @Override
    public RequestEmpathyDTO update(final RequestEmpathyDTO baseDTO) {
        RequestEmpathy updateRequestEmpathy = requestEmpathyRepository.save(RequestEmpathyDTO.builtToDomain(baseDTO));
        return RequestEmpathyDTO.builtToDTO(updateRequestEmpathy);
    }

    @Override
    public void deleteById(final Long id) {
        requestEmpathyRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndContext(final String userId, final RequestDTO context) {
        requestEmpathyRepository.deleteByUserIdAndRequest(userId, RequestDTO.builtToDomain(context));
    }

    @Override
    public boolean existsById(final Long id) {
        return requestEmpathyRepository.existsById(id);
    }

    @Override
    public boolean existsByUserIdAndContext(final String userId, final RequestDTO context) {
        return requestEmpathyRepository.existsByUserIdAndRequest(userId, RequestDTO.builtToDomain(context));
    }

    @Override
    public boolean existsByUserIdAndContextAndStatus(String userId, RequestDTO context, Status status) {
        return requestEmpathyRepository.existsByUserIdAndRequestAndStatus(userId, RequestDTO.builtToDomain(context), status);
    }

    @Override
    public long count() {
        return requestEmpathyRepository.count();
    }

    @Override
    public long countByContextAndStatus(final RequestDTO context, final Status status) {
        return requestEmpathyRepository.countByRequestAndStatus(RequestDTO.builtToDomain(context), status);
    }
}
