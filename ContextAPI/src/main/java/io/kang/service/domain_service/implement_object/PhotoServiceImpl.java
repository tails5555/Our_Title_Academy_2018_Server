package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Photo;
import io.kang.dto.mysql.PhotoDTO;
import io.kang.repository.mysql.PhotoRepository;
import io.kang.service.domain_service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<PhotoDTO> findAll() {
        return photoRepository.findAll().stream()
                .map(photo -> PhotoDTO.builtToDTO(photo))
                .collect(Collectors.toList());
    }

    @Override
    public PhotoDTO getOne(Long id) {
        Photo photo = photoRepository.getOne(id);
        if(photo != null) return PhotoDTO.builtToDTO(photo);
        else return null;
    }

    @Override
    public PhotoDTO findById(Long id) {
        Optional<Photo> tmpPhoto = photoRepository.findById(id);
        if(tmpPhoto.isPresent()) return PhotoDTO.builtToDTO(tmpPhoto.get());
        else return null;
    }

    @Override
    public PhotoDTO create(PhotoDTO photoDTO) {
        Photo createPhoto = photoRepository.save(PhotoDTO.builtToDomain(photoDTO));
        if(createPhoto.getId() != null) return PhotoDTO.builtToDTO(createPhoto);
        else return null;
    }

    @Override
    public PhotoDTO update(PhotoDTO photoDTO) {
        Photo updatePhoto = photoRepository.save(PhotoDTO.builtToDomain(photoDTO));
        return PhotoDTO.builtToDTO(updatePhoto);
    }

    @Override
    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return photoRepository.existsById(id);
    }

    @Override
    public long count() {
        return photoRepository.count();
    }
}
