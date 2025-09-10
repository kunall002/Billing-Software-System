package in.kunal.billingsoftware.service;

import in.kunal.billingsoftware.io.CategoryRequest;
import in.kunal.billingsoftware.io.CategoryResponce;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryResponce add(CategoryRequest request, MultipartFile file);
    List<CategoryResponce>read();
    void delete(String categoryId);
}
