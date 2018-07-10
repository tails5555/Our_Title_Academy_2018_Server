package io.kang.service.domain_service.implement_object;

import io.kang.domain.Profile;
import io.kang.repository.ProfileRepository;
import io.kang.service.domain_service.interfaces.ProfileService;
import io.kang.vo.ProfileVO;
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
    public List<ProfileVO> findAll() {
        return profileRepository.findAll()
                .stream().map((profile) -> ProfileVO.builtToVO(profile))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileVO getOneVO(final Long id) {
        if(profileRepository.existsById(id))
            return ProfileVO.builtToVO(profileRepository.getOne(id));
        else return null;
    }

    @Override
    public ProfileVO findByIdVO(final Long id) {
        Optional<Profile> tmpProfile = profileRepository.findById(id);
        if(tmpProfile.isPresent())
            return ProfileVO.builtToVO(tmpProfile.get());
        else return null;
    }

    @Override
    public ProfileVO create(final ProfileVO profileVO) {
        Profile tmpProfile = ProfileVO.builtToDomain(profileVO);
        Profile createProfile = profileRepository.save(tmpProfile);
        if(createProfile.getId() != null) return ProfileVO.builtToVO(createProfile);
        else return null;
    }

    @Override
    public ProfileVO update(final ProfileVO profileVO) {
        Profile tmpProfile = ProfileVO.builtToDomain(profileVO);
        ProfileVO updateProfile = ProfileVO.builtToVO(profileRepository.save(tmpProfile));
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
