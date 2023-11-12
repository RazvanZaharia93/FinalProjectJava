package com.sda.app.controller;

import com.sda.app.entity.Cart;
import com.sda.app.entity.User;
import com.sda.app.service.CartService;
import com.sda.app.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private CartService cartService;
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCarts(){
        List<Cart> cartList = this.cartService.findAll();
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Lista de cosuri")
                .data(cartList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCart(Cart cart){
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Cos creat cu succes")
                .data(cartService.createCart(cart))
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/")
    public ResponseEntity<ApiResponse> updateCart(Cart cart){
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Cos actualizat cu succes")
                .data(cartService.updateCart(cart))
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteCart(Cart cart){
        cartService.deleteCart(cart);
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Cos sters cu succes")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
