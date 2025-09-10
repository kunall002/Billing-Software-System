package in.kunal.billingsoftware.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String upoloadFile(MultipartFile file);
    boolean deleteFile(String imgUrl);
}
