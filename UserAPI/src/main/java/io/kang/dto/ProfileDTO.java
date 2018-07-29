package io.kang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kang.domain.Profile;
import io.kang.enumeration.Suffix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProfileDTO {
    private Long id;
    private UserDTO user;
    private String fileName;
    private Integer fileSize;
    @JsonIgnore
    private byte[] fileBytes;
    private Suffix fileSuffix;
    private LocalDateTime uploadDate;

    public static ProfileDTO builtToDTO(Profile profile){
        ProfileDTO profileDTO = new ProfileDTO(profile.getId(), UserDTO.builtToDTO(profile.getUser()), profile.getFileName(), profile.getFileSize(), profile.getFileBytes(), profile.getFileSuffix(), profile.getUploadDate());
        return profileDTO;
    }
    public static Profile builtToDomain(ProfileDTO profileDTO){
        Profile profile = new Profile(profileDTO.getId(), UserDTO.builtToDomain(profileDTO.getUser()), profileDTO.getFileName(), profileDTO.getFileSize(), profileDTO.getFileBytes(), profileDTO.getFileSuffix(), profileDTO.getUploadDate());
        return profile;
    }
}
