package io.kang.service.domain_service.implement_object;

import io.kang.domain.City;
import io.kang.model.CityModel;
import io.kang.repository.CityRepository;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityVO> findAll(){
        return cityRepository.findAll()
                .stream().map(city -> CityVO.builtToVO(city))
                .collect(Collectors.toList());
    }

    @Override
    public CityVO getOneVO(final Long id){
        if(cityRepository.existsById(id))
            return CityVO.builtToVO(cityRepository.getOne(id));
        else return null;
    }

    @Override
    public CityVO findByIdVO(final Long id){
        Optional<City> tmpCity = cityRepository.findById(id);
        if(tmpCity.isPresent())
            return CityVO.builtToVO(tmpCity.get());
        return null;
    }

    @Override
    public CityModel getOneModel(final Long id){
        if(cityRepository.existsById(id))
            return CityModel.builtToModel(CityVO.builtToVO(cityRepository.getOne(id)));
        else return null;
    }

    @Override
    public CityModel findByIdModel(final Long id){
        Optional<City> tmpCity = cityRepository.findById(id);
        if(tmpCity.isPresent())
            return CityModel.builtToModel(CityVO.builtToVO(tmpCity.get()));
        return null;
    }

    @Override
    public CityVO create(final CityModel cityModel){
        CityVO cityVO = CityModel.builtToVO(cityModel);
        City createCity = cityRepository.save(CityVO.builtToDomain(cityVO));
        if(createCity.getId() != null) return CityVO.builtToVO(createCity);
        else return null;
    }

    @Override
    public CityVO update(final CityModel cityModel){
        CityVO cityVO = CityModel.builtToVO(cityModel);
        City updateCity = cityRepository.save(CityVO.builtToDomain(cityVO));
        return CityVO.builtToVO(updateCity);
    }

    @Override
    public void deleteById(final Long id){
        cityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id){
        return cityRepository.existsById(id);
    }

    @Override
    public long count(){
        return cityRepository.count();
    }
}
