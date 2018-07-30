package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Title;
import io.kang.dto.mysql.TitleDTO;
import io.kang.repository.mysql.TitleRepository;
import io.kang.service.domain_service.interfaces.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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
    public TitleDTO getOne(Long id) {
        Title title = titleRepository.getOne(id);
        if(title != null)
            return TitleDTO.builtToDTO(title);
        else return null;
    }

    @Override
    public TitleDTO findById(Long id) {
        Optional<Title> tmpTitle = titleRepository.findById(id);
        if(tmpTitle.isPresent())
            return TitleDTO.builtToDTO(tmpTitle.get());
        else return null;
    }

    @Override
    public TitleDTO create(TitleDTO titleDTO) {
        return null;
    }

    @Override
    public TitleDTO update(TitleDTO titleDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
