package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.TitleEmpathy;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.TitleEmpathyRepository;
import io.kang.service.domain_service.interfaces.EmpathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleEmpathyServiceImpl implements EmpathyService<TitleEmpathyDTO, TitleDTO> {
    @Autowired
    private TitleEmpathyRepository titleEmpathyRepository;

    @Override
    public List<TitleEmpathyDTO> findAll() {
        System.out.println(titleEmpathyRepository.findAll());
        return titleEmpathyRepository.findAll().stream()
                .map(titleEmpathy -> TitleEmpathyDTO.builtToDTO(titleEmpathy))
                .collect(Collectors.toList());
    }

    @Override
    public TitleEmpathyDTO getOne(final Long id) {
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(id);
        if(titleEmpathy != null) return TitleEmpathyDTO.builtToDTO(titleEmpathy);
        else return null;
    }

    @Override
    public TitleEmpathyDTO findById(final Long id) {
        Optional<TitleEmpathy> tmpTitleEmpathy = titleEmpathyRepository.findById(id);
        if(tmpTitleEmpathy.isPresent()) return TitleEmpathyDTO.builtToDTO(tmpTitleEmpathy.get());
        else return null;
    }

    @Override
    public TitleEmpathyDTO findByUserIdAndContext(final String userId, final TitleDTO context) {
        Optional<TitleEmpathy> tmpTitleEmpathy = titleEmpathyRepository.findByUserIdAndTitle(userId, TitleDTO.builtToDomain(context));
        if(tmpTitleEmpathy.isPresent()) return TitleEmpathyDTO.builtToDTO(tmpTitleEmpathy.get());
        else return null;
    }

    @Override
    public TitleEmpathyDTO create(final TitleEmpathyDTO baseDTO) {
        TitleEmpathy createTitleEmpathy = titleEmpathyRepository.save(TitleEmpathyDTO.builtToDomain(baseDTO));
        if (createTitleEmpathy.getId() != null) return TitleEmpathyDTO.builtToDTO(createTitleEmpathy);
        else return null;
    }

    @Override
    public TitleEmpathyDTO update(final TitleEmpathyDTO baseDTO) {
        TitleEmpathy updateTitleEmpathy = titleEmpathyRepository.save(TitleEmpathyDTO.builtToDomain(baseDTO));
        return TitleEmpathyDTO.builtToDTO(updateTitleEmpathy);
    }

    @Override
    public void deleteById(final Long id) {
        titleEmpathyRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndContext(final String userId, final TitleDTO context) {
        titleEmpathyRepository.deleteByUserIdAndTitle(userId, TitleDTO.builtToDomain(context));
    }

    @Override
    public boolean existsById(final Long id) {
        return titleEmpathyRepository.existsById(id);
    }

    @Override
    public boolean existsByUserIdAndContext(final String userId, final TitleDTO context) {
        return titleEmpathyRepository.existsByUserIdAndTitle(userId, TitleDTO.builtToDomain(context));
    }

    @Override
    public boolean existsByUserIdAndContextAndStatus(String userId, TitleDTO context, Status status) {
        return titleEmpathyRepository.existsByUserIdAndTitleAndStatus(userId, TitleDTO.builtToDomain(context), status);
    }

    @Override
    public long count() {
        return titleEmpathyRepository.count();
    }

    @Override
    public long countByContextAndStatus(final TitleDTO context, final Status status) {
        return titleEmpathyRepository.countByTitleAndStatus(TitleDTO.builtToDomain(context), status);
    }
}
