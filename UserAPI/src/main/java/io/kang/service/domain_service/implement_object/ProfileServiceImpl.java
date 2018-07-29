package io.kang.service.domain_service.implement_object;

import io.kang.domain.Profile;
import io.kang.dto.ProfileDTO;
import io.kang.repository.ProfileRepository;
import io.kang.service.domain_service.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<ProfileDTO> findAll() {
        return profileRepository.findAll()
                .stream().map((profile) -> ProfileDTO.builtToDTO(profile))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO getOne(final Long id) {
        if(profileRepository.existsById(id))
            return ProfileDTO.builtToDTO(profileRepository.getOne(id));
        else return null;
    }

    @Override
    public ProfileDTO findById(final Long id) {
        Optional<Profile> tmpProfile = profileRepository.findById(id);
        if(tmpProfile.isPresent())
            return ProfileDTO.builtToDTO(tmpProfile.get());
        else return null;
    }

    @Override
    public ProfileDTO findByUserLoginId(String loginId) {
        Optional<Profile> tmpProfile = profileRepository.findByUserLoginId(loginId);
        if(tmpProfile.isPresent()){
            return ProfileDTO.builtToDTO(tmpProfile.get());
        }
        else return null;
    }

    @Override
    public ProfileDTO create(final ProfileDTO profileDTO) {
        Profile tmpProfile = ProfileDTO.builtToDomain(profileDTO);
        Profile createProfile = profileRepository.save(tmpProfile);
        if(createProfile.getId() != null) return ProfileDTO.builtToDTO(createProfile);
        else return null;
    }

    @Override
    public ProfileDTO update(final ProfileDTO profileDTO) {
        Profile tmpProfile = ProfileDTO.builtToDomain(profileDTO);
        ProfileDTO updateProfile = ProfileDTO.builtToDTO(profileRepository.save(tmpProfile));
        return updateProfile;
    }

    @Override
    public void deleteById(final Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return profileRepository.existsById(id);
    }

    @Override
    public long count() {
        return profileRepository.count();
    }
}
