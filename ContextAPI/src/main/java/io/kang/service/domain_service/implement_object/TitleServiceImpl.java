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
import java.util.Random;
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
    public TitleDTO findTopByRequestIdOrderByLikeCountDesc(final Long id) {
        Optional<Title> tmpTitle = titleRepository.findTopByRequestIdOrderByLikeCountDesc(id);
        if(tmpTitle.isPresent()) return TitleDTO.builtToDTO(tmpTitle.get());
        else return null;
    }

    @Override
    public List<TitleDTO> findTop5ByRequestIdOrderByLikeCountDesc(final Long requestId) {
        return titleRepository.findTop5ByRequestIdOrderByLikeCountDesc(requestId).stream()
                .map(title -> TitleDTO.builtToDTO(title))
                .collect(Collectors.toList());
    }

    @Override
    public TitleDTO findByUserIdAndRequest(String userId, RequestDTO requestDTO) {
        Optional<Title> tmpTitle = titleRepository.findByUserIdAndRequest(userId, RequestDTO.builtToDomain(requestDTO));
        if(tmpTitle.isPresent()) return TitleDTO.builtToDTO(tmpTitle.get());
        else return null;
    }

    @Override
    public TitleDTO getRandomTitle(final RequestDTO requestDTO){
        Random random = new Random();
        TitleDTO randomTitleDTO = null;
        List<Title> titleList = titleRepository.findByRequestOrderByWrittenDateDesc(RequestDTO.builtToDomain(requestDTO));
        if(titleList.size() > 0) {
            randomTitleDTO = TitleDTO.builtToDTO(titleList.get(random.nextInt(titleList.size())));
        }
        return randomTitleDTO;
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
    public boolean existsByUserIdAndRequest(String userId, RequestDTO requestDTO) {
        return titleRepository.existsByUserIdAndRequest(userId, RequestDTO.builtToDomain(requestDTO));
    }

    @Override
    public long count() {
        return titleRepository.count();
    }

    @Override
    public long countByRequest(RequestDTO requestDTO) {
        return titleRepository.countByRequest(RequestDTO.builtToDomain(requestDTO));
    }
}
