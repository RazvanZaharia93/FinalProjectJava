package com.sda.app.controller;

import com.sda.app.entity.Category;
import com.sda.app.entity.Item;
import com.sda.app.entity.User;
import com.sda.app.service.ItemService;
import com.sda.app.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllItems() {
        List<Item> itemsList = itemService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Lista items generata cu succes")
                .data(itemsList)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getItemById(@PathVariable("id") Integer id) {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Item by ID")
                .data(itemService.getById(id))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createItem(@RequestBody Item item) {
        Item it = new Item();
        it.setTitle(item.getTitle());
        it.setDescription(item.getDescription());
        it.setCategory(Category.valueOf(item.getCategory().toString()));
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Item creat cu succes")
                .data(itemService.createItem(it))
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateItem(@RequestBody Item item, @PathVariable("id") Integer id) {
        System.out.println(id);
        Item it = new Item();
        it.setId(item.getId());
        it.setTitle(item.getTitle());
        it.setDescription(item.getDescription());
        it.setCategory(Category.valueOf(item.getCategory().toString()));
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Item actualizat cu succes")
                .data(itemService.updateItem(item))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable("id") Integer id) {
        itemService.deleteItem(id);
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Item sters cu succes")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchItem(@RequestParam String word) {
        //http://localhost:8080/items/search?word=test
        //Daca deschidem in browser, ne va afisa din baza de date
        Optional<Item> item = itemService.searchItem(word);
        ApiResponse response = new ApiResponse.Builder().build();
        if (item.isPresent()) {
            response = new ApiResponse.Builder()
                    .status(200)
                    .message("item gasit")
                    .data(item.get())
                    .build();
        } else {
            response = new ApiResponse.Builder()
                    .status(200)
                    .message("item nu a fost gasit")
                    .data(null)
                    .build();
        }

        return ResponseEntity.ok(response);
    }
}

