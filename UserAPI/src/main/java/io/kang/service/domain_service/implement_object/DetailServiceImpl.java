package io.kang.service.domain_service.implement_object;

import io.kang.domain.Detail;
import io.kang.repository.DetailRepository;
import io.kang.service.domain_service.interfaces.DetailService;
import io.kang.vo.DetailVO;
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
    public List<DetailVO> findAll() {
        return detailRepository.findAll()
                .stream().map(detail -> DetailVO.builtToVO(detail))
                .collect(Collectors.toList());
    }

    @Override
    public DetailVO getOneVO(final Long id) {
        if(detailRepository.existsById(id))
            return DetailVO.builtToVO(detailRepository.getOne(id));
        else return null;
    }

    @Override
    public DetailVO findByIdVO(final Long id) {
        Optional<Detail> tmpDetail = detailRepository.findById(id);
        if(tmpDetail.isPresent()) return DetailVO.builtToVO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailVO findByLoginIdVO(final String loginId) {
        Optional<Detail> tmpDetail = detailRepository.findByUserLoginId(loginId);
        if(tmpDetail.isPresent()) return DetailVO.builtToVO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailVO findByNameAndEmailVO(final String name, final String email) {
        Optional<Detail> tmpDetail = detailRepository.findByNameAndEmail(name, email);
        if(tmpDetail.isPresent()) return DetailVO.builtToVO(tmpDetail.get());
        else return null;
    }

    @Override
    public DetailVO create(final DetailVO detailVO) {
        Detail tmpDetail = DetailVO.builtToDomain(detailVO);
        Detail createDetail = detailRepository.save(tmpDetail);
        if(createDetail.getId() != null) return DetailVO.builtToVO(createDetail);
        else return null;
    }

    @Override
    public DetailVO update(final DetailVO detailVO) {
        Detail tmpDetail = DetailVO.builtToDomain(detailVO);
        DetailVO updateDetail = DetailVO.builtToVO(detailRepository.save(tmpDetail));
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
