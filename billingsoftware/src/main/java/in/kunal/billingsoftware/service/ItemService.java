package in.kunal.billingsoftware.service;

import in.kunal.billingsoftware.io.ItemRequest;
import in.kunal.billingsoftware.io.ItemResponce;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemResponce add(ItemRequest request, MultipartFile file);
    List<ItemResponce> fetchItems();
    void deleteItem(String itemId);

}
