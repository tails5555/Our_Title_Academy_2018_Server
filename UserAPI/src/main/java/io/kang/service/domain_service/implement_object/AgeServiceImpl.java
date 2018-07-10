package io.kang.service.domain_service.implement_object;

import io.kang.domain.Age;
import io.kang.model.AgeModel;
import io.kang.repository.AgeRepository;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.vo.AgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgeServiceImpl implements AgeService {
    @Autowired
    private AgeRepository ageRepository;

    @Override
    public List<AgeVO> findAll(){
        return ageRepository.findAll()
                .stream().map(age -> AgeVO.builtToVO(age))
                .collect(Collectors.toList());
    }

    @Override
    public AgeVO getOneVO(final Long id){
        if(ageRepository.existsById(id))
            return AgeVO.builtToVO(ageRepository.getOne(id));
        else return null;
    }

    @Override
    public AgeVO findByIdVO(final Long id){
        Optional<Age> tmpAge = ageRepository.findById(id);
        if(tmpAge.isPresent())
            return AgeVO.builtToVO(tmpAge.get());
        return null;
    }

    @Override
    public AgeModel getOneModel(final Long id){
        if(ageRepository.existsById(id))
            return AgeModel.builtToModel(AgeVO.builtToVO(ageRepository.getOne(id)));
        else return null;
    }

    @Override
    public AgeModel findByIdModel(final Long id){
        Optional<Age> tmpAge = ageRepository.findById(id);
        if(tmpAge.isPresent())
            return AgeModel.builtToModel(AgeVO.builtToVO(tmpAge.get()));
        return null;
    }

    @Override
    public AgeVO create(final AgeModel ageModel){
        AgeVO ageVO = AgeModel.builtToVO(ageModel);
        Age createAge = ageRepository.save(AgeVO.builtToDomain(ageVO));
        if(createAge.getId() != null) return AgeVO.builtToVO(createAge);
        else return null;
    }

    @Override
    public AgeVO update(final AgeModel ageModel){
        AgeVO ageVO = AgeModel.builtToVO(ageModel);
        Age updateAge = ageRepository.save(AgeVO.builtToDomain(ageVO));
        return AgeVO.builtToVO(updateAge);
    }

    @Override
    public void deleteById(final Long id){
        ageRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id){
        return ageRepository.existsById(id);
    }

    @Override
    public long count(){
        return ageRepository.count();
    }
}
