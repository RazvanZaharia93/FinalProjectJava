package com.sda.app.controller;

import com.sda.app.entity.User;
import com.sda.app.service.UserService;
import com.sda.app.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")//adresa unde gasim metodele pt user
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUsers(){
        List<User> userList = this.userService.findAll();
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("lista utilizatori generata")
                .data(userList)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/hello")
    public String sayHello(){
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("12");
        user.setUsername("test");
        this.userService.createUser(user);
        return "Is working";
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user){
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Utilizator creat cu succes")
                .data(userService.createUser(user))
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/")
    public ResponseEntity<ApiResponse> updateUser(User user){
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Utilizator actualizat cu succes")
                .data(userService.updateUser(user))
                .build();
        return ResponseEntity.ok(response);
    }
@DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteUser(User user){
        userService.deleteUser(user);
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Utilizator sters cu succes")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(User user){
        ApiResponse response= new ApiResponse.Builder()
                .status(200)
                .message("Utilizator sters cu succes")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user){
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Registered")
                .data(this.userService.createUser(user))
                .build();
        return ResponseEntity.ok(response);
    }
}
