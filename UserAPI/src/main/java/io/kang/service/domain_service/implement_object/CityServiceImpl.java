package io.kang.service.domain_service.implement_object;

import io.kang.domain.City;
import io.kang.dto.CityDTO;
import io.kang.repository.CityRepository;
import io.kang.service.domain_service.interfaces.CityService;
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
    public List<CityDTO> findAll(){
        return cityRepository.findAll()
                .stream().map(city -> CityDTO.builtToDTO(city))
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getOne(final Long id){
        if(cityRepository.existsById(id))
            return CityDTO.builtToDTO(cityRepository.getOne(id));
        else return null;
    }

    @Override
    public CityDTO findById(final Long id){
        Optional<City> tmpCity = cityRepository.findById(id);
        if(tmpCity.isPresent())
            return CityDTO.builtToDTO(tmpCity.get());
        return null;
    }

    @Override
    public CityDTO create(final CityDTO cityDTO){
        City createCity = cityRepository.save(CityDTO.builtToDomain(cityDTO));
        if(createCity.getId() != null) return CityDTO.builtToDTO(createCity);
        else return null;
    }

    @Override
    public CityDTO update(final CityDTO cityDTO){
        City updateCity = cityRepository.save(CityDTO.builtToDomain(cityDTO));
        return CityDTO.builtToDTO(updateCity);
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
