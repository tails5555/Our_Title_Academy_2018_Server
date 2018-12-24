package io.kang.rest_controller;

import io.kang.dto.mysql.PhotoDTO;
import io.kang.service.integrate_service.interfaces.PhotoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI/photo/")
public class PhotoRestController {
    @Autowired
    private PhotoFetchService photoFetchService;

    private HttpHeaders generateImageHeader(PhotoDTO photoDTO){
        HttpHeaders header = new HttpHeaders();
        switch(photoDTO.getFileSuffix()) {
            case JPG :
            case JPEG :
                header.setContentType(MediaType.IMAGE_JPEG);
                break;
            case PNG :
                header.setContentType(MediaType.IMAGE_PNG);
                break;
            case GIF :
                header.setContentType(MediaType.IMAGE_GIF);
                break;
        }
        header.setContentDispositionFormData("attachment", photoDTO.getFileName());
        return header;
    }

    @GetMapping("/request_image/{requestId}")
    public ResponseEntity<?> fetchByRequest(@PathVariable Long requestId){
        PhotoDTO photoDTO = photoFetchService.fetchByRequestId(requestId);
        if(photoDTO != null){
            HttpHeaders headers = this.generateImageHeader(photoDTO);
            return new ResponseEntity<byte[]>(photoDTO.getFileBytes(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
