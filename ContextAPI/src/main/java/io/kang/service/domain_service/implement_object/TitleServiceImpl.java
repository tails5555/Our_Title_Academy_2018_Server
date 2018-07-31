package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Title;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.repository.mysql.TitleRepository;
import io.kang.service.domain_service.interfaces.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<TitleDTO> findAll() {
        return titleRepository.findAll().stream()
                .map(title -> TitleDTO.builtToDTO(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<TitleDTO> findAllByOrderByWrittenDateDesc() {
        return titleRepository.findAllByOrderByWrittenDateDesc().stream()
                .map(title -> TitleDTO.builtToDTO(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<TitleDTO> findByUserIdOrderByWrittenDateDesc(final String userId) {
        return titleRepository.findByUserIdOrderByWrittenDateDesc(userId).stream()
                .map(title -> TitleDTO.builtToDTO(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<TitleDTO> findByRequestOrderByWrittenDateDesc(final RequestDTO requestDTO) {
        return titleRepository.findByRequestOrderByWrittenDateDesc(RequestDTO.builtToDomain(requestDTO)).stream()
                .map(title -> TitleDTO.builtToDTO(title))
                .collect(Collectors.toList());
    }

    @Override
    public TitleDTO getOne(final Long id) {
        Title title = titleRepository.getOne(id);
        if(title != null) return TitleDTO.builtToDTO(title);
        else return null;
    }

    @Override
    public TitleDTO findById(final Long id) {
        Optional<Title> tmpTitle = titleRepository.findById(id);
        if(tmpTitle.isPresent()) return TitleDTO.builtToDTO(tmpTitle.get());
        else return null;
    }

    @Override
    public TitleDTO create(final TitleDTO titleDTO) {
        Title createTitle = titleRepository.save(TitleDTO.builtToDomain(titleDTO));
        if(createTitle.getId() != null) return TitleDTO.builtToDTO(createTitle);
        return null;
    }

    @Override
    public TitleDTO update(final TitleDTO titleDTO) {
        Title updateTitle = titleRepository.save(TitleDTO.builtToDomain(titleDTO));
        return TitleDTO.builtToDTO(updateTitle);
    }

    @Override
    public void deleteById(final Long id) {
        titleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return titleRepository.existsById(id);
    }

    @Override
    public long count() {
        return titleRepository.count();
    }
}
