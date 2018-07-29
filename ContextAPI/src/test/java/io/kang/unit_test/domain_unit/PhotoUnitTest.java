package io.kang.unit_test.domain_unit;

import io.kang.domain.mysql.Photo;
import io.kang.domain.mysql.Request;
import io.kang.enumeration.Suffix;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class PhotoUnitTest {
    private static final long ID = 1L;
    private static final String USER_ID = "PHOTO_USER_ID01";
    private static final Request REQUEST = RequestUpdateSingleton.INSTANCE.getInstance();
    private static final String FILE_NAME = "PHOTO_FILE_NAME01";
    private static final int FILE_SIZE = 0;
    private static final byte[] FILE_BYTES = new byte[0];
    private static final Suffix FILE_SUFFIX = Suffix.PNG;
    private static final LocalDateTime UPLOAD_DATE = LocalDateTime.MIN;

    @Test
    public void id_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setId(ID);
        long id = photo.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setUserId(USER_ID);
        String userId = photo.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void request_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setRequest(REQUEST);
        Request request = photo.getRequest();
        Assert.assertEquals(request, REQUEST);
    }

    @Test
    public void file_name_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setFileName(FILE_NAME);
        String fileName = photo.getFileName();
        Assert.assertEquals(fileName, FILE_NAME);
    }

    @Test
    public void file_size_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setFileSize(FILE_SIZE);
        int fileSize = photo.getFileSize();
        Assert.assertEquals(fileSize, FILE_SIZE);
    }

    @Test
    public void file_bytes_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setFileBytes(FILE_BYTES);
        byte[] fileBytes = photo.getFileBytes();
        Assert.assertEquals(fileBytes, FILE_BYTES);
    }

    @Test
    public void file_suffix_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setFileSuffix(FILE_SUFFIX);
        Suffix fileSuffix = photo.getFileSuffix();
        Assert.assertEquals(fileSuffix, FILE_SUFFIX);
    }

    @Test
    public void upload_date_getter_and_setter_test(){
        Photo photo = new Photo();
        photo.setUploadDate(UPLOAD_DATE);
        LocalDateTime uploadDate = photo.getUploadDate();
        Assert.assertEquals(uploadDate, UPLOAD_DATE);
    }

    @Test
    public void equals_test(){
        Photo photo = new Photo();
        photo.setId(ID);
        photo.setUserId(USER_ID);
        photo.setRequest(REQUEST);
        photo.setFileName(FILE_NAME);
        photo.setFileSize(FILE_SIZE);
        photo.setFileBytes(FILE_BYTES);
        photo.setFileSuffix(FILE_SUFFIX);
        photo.setUploadDate(UPLOAD_DATE);

        Photo samePhoto = new Photo(ID, USER_ID, REQUEST, FILE_NAME, FILE_SIZE, FILE_BYTES, FILE_SUFFIX, UPLOAD_DATE);
        Assert.assertEquals(photo, samePhoto);
    }

    @Test
    public void to_string_test(){
        Photo photo = new Photo();
        photo.setId(ID);
        photo.setUserId(USER_ID);
        photo.setRequest(REQUEST);
        photo.setFileName(FILE_NAME);
        photo.setFileSize(FILE_SIZE);
        photo.setFileBytes(FILE_BYTES);
        photo.setFileSuffix(FILE_SUFFIX);
        photo.setUploadDate(UPLOAD_DATE);

        String string = photo.toString();
        Assert.assertEquals("Photo(id=1, userId=PHOTO_USER_ID01, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0), fileName=PHOTO_FILE_NAME01, fileSize=0, fileBytes=[], fileSuffix=PNG, uploadDate=-999999999-01-01T00:00)", string);
    }
}
