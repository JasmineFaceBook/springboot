package myTest;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

    private MultipartFile getMultipartFile(File file){

        FileInputStream input = null;
        try {
            input = new FileInputStream(file);

        MultipartFile multipartFile =new MockMultipartFile("file", file.getName(),
                "text/plain", IOUtils.toByteArray(input));
        return multipartFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
