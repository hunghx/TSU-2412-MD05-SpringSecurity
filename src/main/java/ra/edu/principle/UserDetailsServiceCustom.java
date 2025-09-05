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
        List<SimpleGrantedAuthority> grantedAuthorities = user.getRoles()
                .stream().map(role->new SimpleGrantedAuthority(role.getRoleName().name()))
                .toList(); // ROLE_
        UserDetails userDetails = UserDetailsCustom.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
        return userDetails;
    }
}
