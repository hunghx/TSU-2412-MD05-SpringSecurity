package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.edu.dto.ResponseData;
import ra.edu.entity.Account;
import ra.edu.exception.NotFoundException;
import ra.edu.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(){
        return "success";
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getUser(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.getById(id));// 200
    }
}
