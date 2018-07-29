package io.kang.service.domain_service.implement_object;

import io.kang.domain.Age;
import io.kang.dto.AgeDTO;
import io.kang.repository.AgeRepository;
import io.kang.service.domain_service.interfaces.AgeService;
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
    public List<AgeDTO> findAll(){
        return ageRepository.findAll()
                .stream().map(age -> AgeDTO.builtToDTO(age))
                .collect(Collectors.toList());
    }

    @Override
    public AgeDTO getOne(final Long id){
        if(ageRepository.existsById(id))
            return AgeDTO.builtToDTO(ageRepository.getOne(id));
        else return null;
    }

    @Override
    public AgeDTO findById(final Long id){
        Optional<Age> tmpAge = ageRepository.findById(id);
        if(tmpAge.isPresent())
            return AgeDTO.builtToDTO(tmpAge.get());
        return null;
    }

    @Override
    public AgeDTO create(final AgeDTO ageDTO){
        Age createAge = ageRepository.save(AgeDTO.builtToDomain(ageDTO));
        if(createAge.getId() != null) return AgeDTO.builtToDTO(createAge);
        else return null;
    }

    @Override
    public AgeDTO update(final AgeDTO ageDTO){
        Age updateAge = ageRepository.save(AgeDTO.builtToDomain(ageDTO));
        return AgeDTO.builtToDTO(updateAge);
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
