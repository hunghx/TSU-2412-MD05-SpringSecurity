package ra.edu.config.principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.edu.config.entity.Account;
import ra.edu.config.repository.IAccountRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username : "+username + " not exist!"));
        GrantedAuthority userRole = new SimpleGrantedAuthority("USER");
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(userRole);
        UserDetails userDetails = UserDetailsCustom.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
        return userDetails;
    }
}
