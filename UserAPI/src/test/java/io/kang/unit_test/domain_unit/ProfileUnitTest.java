package io.kang.unit_test.domain_unit;

import io.kang.domain.Profile;
import io.kang.domain.User;
import io.kang.enumeration.Suffix;
import io.kang.unit_test.domain_unit.singleton_object.UserSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ProfileUnitTest {
    private static final long ID = 1L;
    private static final User USER = UserSingleton.INSTANCE.getInstance();
    private static final String FILE_NAME = "PROFILE_FILE_NAME01";
    private static final int FILE_SIZE = 0;
    private static final byte[] FILE_BYTES = new byte[0];
    private static final Suffix FILE_SUFFIX = Suffix.PNG;
    private static final LocalDateTime UPLOAD_DATE = LocalDateTime.now();

    @Test
    public void idSetterAndGetterTesting() throws IOException {
        Profile profile = new Profile();
        profile.setId(ID);
        long id = profile.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void userSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setUser(USER);
        User user = profile.getUser();
        Assert.assertEquals(user, USER);
    }

    @Test
    public void fileNameSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setFileName(FILE_NAME);
        String fileName = profile.getFileName();
        Assert.assertEquals(FILE_NAME, fileName);
    }

    @Test
    public void fileSizeSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setFileSize(FILE_SIZE);
        int fileSize = profile.getFileSize();
        Assert.assertEquals(fileSize, FILE_SIZE);
    }

    @Test
    public void fileBytesSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setFileBytes(FILE_BYTES);
        byte[] fileBytes = profile.getFileBytes();
        Assert.assertEquals(fileBytes, FILE_BYTES);
    }

    @Test
    public void fileSuffixSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setFileSuffix(FILE_SUFFIX);
        Suffix fileSuffix = profile.getFileSuffix();
        Assert.assertEquals(fileSuffix, FILE_SUFFIX);
    }

    @Test
    public void uploadDateSetterAndGetterTesting() throws IOException{
        Profile profile = new Profile();
        profile.setUploadDate(UPLOAD_DATE);
        LocalDateTime uploadDate = profile.getUploadDate();
        Assert.assertEquals(uploadDate, UPLOAD_DATE);
    }

    @Test
    public void equalsTesting() throws IOException{
        Profile profile = new Profile();
        profile.setId(ID);
        profile.setUser(USER);
        profile.setFileName(FILE_NAME);
        profile.setFileBytes(FILE_BYTES);
        profile.setFileSize(FILE_SIZE);
        profile.setFileSuffix(FILE_SUFFIX);
        profile.setUploadDate(UPLOAD_DATE);

        Profile sameProfile = new Profile(ID, USER, FILE_NAME, FILE_SIZE, FILE_BYTES, FILE_SUFFIX, UPLOAD_DATE);

        Assert.assertTrue(profile.equals(profile));
    }

    @Test
    public void toStringTesting() throws IOException{
        Profile profile = new Profile();
        profile.setId(ID);
        profile.setUser(USER);
        profile.setFileName(FILE_NAME);
        profile.setFileBytes(FILE_BYTES);
        profile.setFileSize(FILE_SIZE);
        profile.setFileSuffix(FILE_SUFFIX);
        profile.setUploadDate(UPLOAD_DATE);

        String string = profile.toString();
        String cmpResult = String.format("Profile(id=%d, user=%s, fileName=%s, fileSize=%d, fileBytes=%s, fileSuffix=%s, uploadDate=%s)", ID, USER, FILE_NAME, FILE_SIZE, Arrays.toString(FILE_BYTES), FILE_SUFFIX, UPLOAD_DATE);

        Assert.assertEquals(string, cmpResult);
    }
}
