package ra.edu.principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.edu.entity.Account;
import ra.edu.repository.IAccountRepository;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username : "+username + " not exist!"));
        System.out.println(user.getEmail());
        GrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        System.out.println(userRole.getAuthority().getBytes(StandardCharsets.UTF_8));
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(userRole);
        UserDetails userDetails = UserDetailsCustom.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
        return userDetails;
    }
}
