package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.dto.FormLogin;
import ra.edu.dto.ResponseData;
import ra.edu.service.AuthenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenService authenService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody FormLogin formLogin){
        ResponseData responseData = authenService.login(formLogin);
        if (responseData.isSuccess()){
            return ResponseEntity.ok(responseData); // 200
        }else {
            return ResponseEntity.badRequest().body(responseData); // 400
        }
    }


}
