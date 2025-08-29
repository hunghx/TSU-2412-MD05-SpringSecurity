package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(){
        return "success";
    }
}
