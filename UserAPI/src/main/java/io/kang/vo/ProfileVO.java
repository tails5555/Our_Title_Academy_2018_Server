package io.kang.vo;

import io.kang.domain.Profile;
import io.kang.enumeration.Suffix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProfileVO {
    private Long id;
    private UserVO user;
    private String fileName;
    private Integer fileSize;
    private byte[] fileBytes;
    private Suffix fileSuffix;
    private LocalDateTime uploadDate;
    public static ProfileVO builtToVO(Profile profile){
        ProfileVO profileVO = new ProfileVO(profile.getId(), UserVO.builtToVO(profile.getUser()), profile.getFileName(), profile.getFileSize(), profile.getFileBytes(), profile.getFileSuffix(), profile.getUploadDate());
        return profileVO;
    }
    public static Profile builtToDomain(ProfileVO profileVO){
        Profile profile = new Profile(profileVO.getId(), UserVO.builtToDomain(profileVO.getUser()), profileVO.getFileName(), profileVO.getFileSize(), profileVO.getFileBytes(), profileVO.getFileSuffix(), profileVO.getUploadDate());
        return profile;
    }
}
