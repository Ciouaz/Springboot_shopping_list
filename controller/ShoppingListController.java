package com.shoppinglistapp.controller;

import com.shoppinglistapp.dto.ShoppingList;
import com.shoppinglistapp.dto.ShoppingListList;
import com.shoppinglistapp.service.ShoppingListService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shoppingList")
@AllArgsConstructor
public class ShoppingListController {
    private final ShoppingListService service;

    @GetMapping("/getProduct/{id}")
    public ShoppingList getProduct(@PathVariable("id") Long id) {
        return (ShoppingList) service.getProduct(id);
    }

    @PostMapping("/addProduct")
    public ShoppingList saveProduct(@RequestBody ShoppingList body){
        return service.saveProduct(body);
    }

    @GetMapping("/getWholeList")
    ResponseEntity<ShoppingListList> getShoppingList(){
        ShoppingListList shoppingListList = service.getList();
        return ResponseEntity.ok(shoppingListList);
    }

//    @PostMapping("/createProduct")
//    ResponseEntity<Void> createProduct(@Valid @RequestBody ShoppingList requestBody) {
//        Long newResourceId = service.create(requestBody);
//
//        URI newResourceLocation = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(newResourceId)
//                .toUri();
//
//        return ResponseEntity.created(newResourceLocation).body(null);
//    }

    @PutMapping("/update/{id}")
    ResponseEntity<Void> updateList(@PathVariable("id") Long id,
                                    @RequestBody @Valid ShoppingList requestBody) {
        boolean result = service.update(id, requestBody);
        if (!result) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/deleteProduct/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
