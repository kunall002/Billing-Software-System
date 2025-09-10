package in.kunal.billingsoftware.service.impl;

import in.kunal.billingsoftware.entity.CategoryEntity;
import in.kunal.billingsoftware.io.CategoryRequest;
import in.kunal.billingsoftware.io.CategoryResponce;
import in.kunal.billingsoftware.repository.CategoryRepository;
import in.kunal.billingsoftware.service.CategoryService;
import in.kunal.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponce add(CategoryRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.upoloadFile(file);
        CategoryEntity newCategory = convertToEntity(request);
        newCategory.setImgurl(imgUrl);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponce(newCategory);
    }

    @Override
    public List<CategoryResponce> read() {
       return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponce(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
       CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId)
               .orElseThrow(() -> new RuntimeException("Category not found: "+categoryId ));
       fileUploadService.deleteFile(existingCategory.getImgurl());
       categoryRepository.delete(existingCategory);
    }

    private CategoryResponce convertToResponce(CategoryEntity newCategory) {
            return CategoryResponce.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                 .imgUrl(newCategory.getImgurl())
                 .createdAt(newCategory.getCreatedAt())
                 .updatedAt(newCategory.getUpdatedAt())
                 .build();

    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
