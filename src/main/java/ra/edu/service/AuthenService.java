package ra.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ra.edu.config.jwt.JwtProvider;
import ra.edu.dto.FormLogin;
import ra.edu.dto.ResponseData;
import ra.edu.entity.Account;
import ra.edu.repository.IAccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IAccountRepository accountRepository;
    public ResponseData login(FormLogin formLogin){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassword()));
        }catch(Exception e){
            return ResponseData.builder()
                    .success(false)
                    .message("Username or Pass incorrect")
                    .build();
        }
        Account user = accountRepository.findByEmail(formLogin.getUsername()).orElseThrow();
        // tao token vaf tra ve thog tin
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtProvider.generateToken(formLogin.getUsername()));
        map.put("email", user.getEmail() );
        map.put("role", user.getRoles());
        // ...
        return ResponseData.builder()
                .success(true)
                .message("Login successfully")
                .data(map)
                .build();
    }
}
