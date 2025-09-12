package in.kunal.billingsoftware.service.impl;

import in.kunal.billingsoftware.entity.CategoryEntity;
import in.kunal.billingsoftware.entity.ItemEntity;
import in.kunal.billingsoftware.io.ItemRequest;
import in.kunal.billingsoftware.io.ItemResponce;
import in.kunal.billingsoftware.repository.CategoryRepository;
import in.kunal.billingsoftware.repository.ItemRepository;
import in.kunal.billingsoftware.service.FileUploadService;
import in.kunal.billingsoftware.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemResponce add(ItemRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.upoloadFile(file);
        ItemEntity newItem = convertToEntity(request);
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found : "+ request.getCategoryId()));
        newItem.setCategory(existingCategory);
        newItem.setImgUrl(imgUrl);
        newItem = itemRepository.save(newItem);
        return convertToResponce(newItem);
    }

    private ItemResponce convertToResponce(ItemEntity newItem) {
            return ItemResponce.builder()
                    .itemId(newItem.getItemId())
                    .name(newItem.getName())
                    .description(newItem.getDescription())
                    .price(newItem.getPrice())
                    .imgURl(newItem.getImgUrl())
                    .categoryName(newItem.getCategory().getCategoryId())
                    .categoryId(newItem.getCategory().getCategoryId())
                    .createdAt(newItem.getCreatedAt())
                    .updatedAt(newItem.getUpdatedAt())
                    .build();
    }
    private ItemEntity convertToEntity(ItemRequest request) {
            return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }


    @Override
    public List<ItemResponce> fetchItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> convertToResponce(itemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String itemId) {
        ItemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(()-> new RuntimeException("Item not found: "+ itemId));
        boolean isFileDeleted = fileUploadService.deleteFile(existingItem.getImgUrl());
        if (isFileDeleted){
            itemRepository.delete(existingItem);
        }else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the image");
        }
    }
}
