package io.kang.dto.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kang.domain.mysql.Photo;
import io.kang.enumeration.Suffix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class PhotoDTO {
    private Long id;
    private String userId;
    private RequestDTO request;
    private String fileName;
    private Integer fileSize;
    @JsonIgnore
    private byte[] fileBytes;
    private Suffix fileSuffix;
    private LocalDateTime uploadDate;
    public static PhotoDTO builtToDTO(Photo photo){
        return new PhotoDTO(photo.getId(), photo.getUserId(), RequestDTO.builtToDTO(photo.getRequest()), photo.getFileName(), photo.getFileSize(), photo.getFileBytes(), photo.getFileSuffix(), photo.getUploadDate());
    }
    public static Photo builtToDomain(PhotoDTO photoDTO){
        return new Photo(photoDTO.getId(), photoDTO.getUserId(), RequestDTO.builtToDomain(photoDTO.getRequest()), photoDTO.getFileName(), photoDTO.getFileSize(), photoDTO.getFileBytes(), photoDTO.getFileSuffix(), photoDTO.getUploadDate());
    }
}
