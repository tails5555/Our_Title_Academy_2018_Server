package io.kang.service.domain_service.implement_object;

import io.kang.domain.Detail;
import io.kang.dto.DetailDTO;
import io.kang.repository.DetailRepository;
import io.kang.service.domain_service.interfaces.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailRepository detailRepository;

    @Override
    public List<DetailDTO> findAll() {
        return detailRepository.findAll()
                .stream().map(detail -> DetailDTO.builtToDTO(detail))
                .collect(Collectors.toList());
    }

    @Override
    public DetailDTO getOne(final Long id) {
        if(detailRepository.existsById(id))
            return DetailDTO.builtToDTO(detailRepository.getOne(id));
        else return null;
    }

    @Override
    public DetailDTO findById(final Long id) {
        Optional<Detail> tmpDetail = detailRepository.findById(id);
        if(tmpDetail.isPresent()) return DetailDTO.builtToDTO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailDTO findByLoginId(final String loginId) {
        Optional<Detail> tmpDetail = detailRepository.findByUserLoginId(loginId);
        if(tmpDetail.isPresent()) return DetailDTO.builtToDTO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailDTO findByNameAndEmail(final String name, final String email) {
        Optional<Detail> tmpDetail = detailRepository.findByNameAndEmail(name, email);
        if(tmpDetail.isPresent()) return DetailDTO.builtToDTO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailDTO create(final DetailDTO detailVO) {
        Detail tmpDetail = DetailDTO.builtToDomain(detailVO);
        Detail createDetail = detailRepository.save(tmpDetail);
        if(createDetail.getId() != null) return DetailDTO.builtToDTO(createDetail);
        else return null;
    }

    @Override
    public DetailDTO update(final DetailDTO detailVO) {
        Detail tmpDetail = DetailDTO.builtToDomain(detailVO);
        DetailDTO updateDetail = DetailDTO.builtToDTO(detailRepository.save(tmpDetail));
        return updateDetail;
    }

    @Override
    public void deleteById(final Long id) {
        detailRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return detailRepository.existsById(id);
    }

    @Override
    public long count() {
        return detailRepository.count();
    }
}
