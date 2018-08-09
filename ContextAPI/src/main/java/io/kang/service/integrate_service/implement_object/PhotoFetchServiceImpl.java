package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.PhotoDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.enumeration.Suffix;
import io.kang.service.domain_service.interfaces.PhotoService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.integrate_service.interfaces.PhotoFetchService;
import io.kang.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PhotoFetchServiceImpl implements PhotoFetchService {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private RequestService requestService;

    private String fileNameEncryption(final String fileName) {
        int infix=fileName.lastIndexOf('.');
        String fileSuffix = fileName.substring(infix, fileName.length());
        String filePrefix = Encryption.encrypt(fileName.substring(0, infix-1), Encryption.SHA256);
        return filePrefix + fileSuffix;
    }

    @Override
    @Transactional
    public void photoUpload(final MultipartFile file, final Long requestId, final String userId) throws IOException {
        byte[] fileBytes = file.getBytes();
        if(fileBytes == null || fileBytes.length <= 0) return;

        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO == null) return;

        PhotoDTO photoDTO = photoService.findByRequestDTO(requestDTO);

        try( ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes) ) {
            String fileName = file.getOriginalFilename();
            int infix = fileName.lastIndexOf('.');
            String fileSuffix = fileName.substring(infix + 1, fileName.length());
            if(photoDTO != null){
                photoDTO.setUserId(userId);
                photoDTO.setRequest(requestDTO);
                photoDTO.setFileName(this.fileNameEncryption(fileName));
                photoDTO.setFileSize(fileBytes.length);
                photoDTO.setFileBytes(fileBytes);
                photoDTO.setFileSuffix(Suffix.valueOf(fileSuffix.toUpperCase()));
                photoDTO.setUploadDate(LocalDateTime.now());
                photoService.update(photoDTO);
            }else{
                photoDTO = new PhotoDTO(0L, userId, requestDTO, this.fileNameEncryption(fileName), fileBytes.length, fileBytes, Suffix.valueOf(fileSuffix.toUpperCase()), LocalDateTime.now());
                photoService.create(photoDTO);
            }
        } catch (IOException e){
            throw e;
        }
    }

    @Override
    public PhotoDTO fetchByRequestId(Long requestId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO == null) return null;

        PhotoDTO photoDTO = photoService.findByRequestDTO(requestDTO);
        if(photoDTO == null || photoDTO.getFileSize() <= 0) return null;
        else return photoDTO;
    }
}
