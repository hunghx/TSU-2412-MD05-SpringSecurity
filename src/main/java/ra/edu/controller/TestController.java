package ra.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/user/data")
    public ResponseEntity<String> getUserData(){
        return ResponseEntity.ok("Hello World");
    }
    @GetMapping("/api/admin/data")
    public ResponseEntity<String> getAdminData(){
        return ResponseEntity.ok("Hello World");
    }
    @GetMapping("/api/manager/data")
    public ResponseEntity<String> getManagerData(){
        return ResponseEntity.ok("Hello World");
    }
}
