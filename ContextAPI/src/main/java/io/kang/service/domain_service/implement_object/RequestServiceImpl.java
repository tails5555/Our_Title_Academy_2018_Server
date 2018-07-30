package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Request;
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
    public RequestDTO getOne(Long id) {
        Request request = requestRepository.getOne(id);
        if(request != null) return RequestDTO.builtToDTO(request);
        else return null;
    }

    @Override
    public RequestDTO findById(Long id) {
        Optional<Request> tmpRequest = requestRepository.findById(id);
        if(tmpRequest.isPresent()) return RequestDTO.builtToDTO(tmpRequest.get());
        else return null;
    }

    @Override
    public RequestDTO create(RequestDTO requestDTO) {
        Request createRequest = requestRepository.save(RequestDTO.builtToDomain(requestDTO));
        if(createRequest.getId() != null) return RequestDTO.builtToDTO(createRequest);
        else return null;
    }

    @Override
    public RequestDTO update(RequestDTO requestDTO) {
        Request updateRequest = requestRepository.save(RequestDTO.builtToDomain(requestDTO));
        return RequestDTO.builtToDTO(updateRequest);
    }

    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return requestRepository.existsById(id);
    }

    @Override
    public long count() {
        return requestRepository.count();
    }
}
