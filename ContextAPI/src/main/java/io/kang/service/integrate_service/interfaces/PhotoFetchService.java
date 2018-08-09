package io.kang.service.integrate_service.interfaces;

import io.kang.dto.mysql.PhotoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoFetchService {
    public void photoUpload(final MultipartFile file, final Long requestId, final String userId) throws IOException;
    public PhotoDTO fetchByRequestId(final Long requestId);
}
